package com.aigodata.jessica.basic.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@PropertySource(value = { "classpath:generate.properties" })
@Controller
@RequestMapping("/")
public class MakeCode {
	public static SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Value("${spring.datasource.mysql.url}")
	private String url;
	@Value("${spring.datasource.mysql.username}")
	private String username;
	@Value("${spring.datasource.mysql.password}")
	private String password;
	@Value("${generate.basepackage}")
	private String generatePackage;
	@Value("${generate.author}")
	private String generateAuthor;
	@Value("${generate.savepath}")
	private String generateSavepath;
	@Value("${project.package}")
	private String projectPackage;

	@RequestMapping("/makecode")
	public void run() {
		try {
			System.out.println("自动生成代码开始...");
			String tmp = url.split("\\?")[0];
			String catalog = tmp.substring(tmp.lastIndexOf("/") + 1);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement psTable = conn.prepareStatement(String.format(
					"select table_name,create_time,table_comment from information_schema.`tables` where table_schema = '%s' ",
					catalog));
			ResultSet rsTable = psTable.executeQuery();
			while (rsTable.next()) {
				StringBuffer sb = new StringBuffer();
				String tableName = rsTable.getString("table_name");
				String tableCommment = rsTable.getString("table_comment").trim();

				StringBuffer buffImport = imports();
				StringBuffer buffClass = classes(tableName, tableCommment);
				StringBuffer buffFields = new StringBuffer();
				StringBuffer buffProps = new StringBuffer();

				PreparedStatement ps = conn.prepareStatement(String.format(
						"select column_name,data_type,column_comment,column_key from information_schema.`columns` where table_schema = '%s' and table_name = '%s'",
						catalog, tableName));
				ResultSet rs = ps.executeQuery();
				List<String[]> data = new ArrayList<String[]>();
				while (rs.next()) {
					String name = rs.getString(1);
					String type = rs.getString(2);
					String comment = rs.getString(3);
					String columnKey = rs.getString(4);
					if (comment.trim().length() == 0) {
						comment = name;
					}
					if (type.contains("bigint")) {
						type = "Long";
					} else if (type.contains("int")) {
						type = "Integer";
					} else if (type.contains("decimal")) {
						type = "Double";
					} else if (type.contains("varchar") || type.contains("text")) {
						type = "String";
					} else if (type.contains("date") || type.contains("datetime") || type.contains("timestamp")) {
						type = "Date";
						newLine(buffImport, "import java.util.Date;");
					} else if (type.contains("binary")) {
						type = "Byte[]";
					}
					data.add(new String[] { name, type, comment, columnKey });
				}

				for (String[] ds : data) {
					String name = ds[0];
					String type = ds[1];
					String comment = ds[2];
					String columnKey = ds[3];
					if ("PRI".equals(columnKey)) {
						newLine(buffFields, "@Id");
					}
					fields(buffFields, name, type, comment);
				}

				for (String[] ds : data) {
					String name = ds[0];
					String type = ds[1];
					props(buffProps, name, type);
				}

				sb.append(packages("domain"));
				sb.append(buffImport);
				sb.append(buffClass);
				sb.append(buffFields);
				sb.append(buffProps);
				sb.append("}");

				write(generateSavepath + "/domain/" + converClass(tableName) + ".java", sb.toString());
				write(generateSavepath + "/service/" + converClass(tableName) + "Service.java", service(tableName));
				write(generateSavepath + "/service/impl/" + converClass(tableName) + "ServiceImpl.java",
						serviceImpl(tableName));
				write(generateSavepath + "/mapper/" + converClass(tableName) + "Mapper.java", mapper(tableName));
			}
			System.out.println("自动生成代码结束...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String service(String tableName) {
		try {
			StringBuffer sb = new StringBuffer();
			newLine(sb, packages("service"));
			newLine(sb, "public interface " + converClass(tableName) + "Service {");
			newLine(sb, "}");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String serviceImpl(String tableName) {
		try {
			StringBuffer sb = new StringBuffer();
			newLine(sb, packages("service.impl"));
			newLine(sb, "import org.springframework.stereotype.Service;");
			newLine(sb, "import org.springframework.transaction.annotation.Transactional;");
			newLine(sb, "import " + generatePackage + ".service." + converClass(tableName) + "Service;");
			newLine(sb, "@Service");
			newLine(sb, "@Transactional");
			newLine(sb, "public class " + converClass(tableName) + "ServiceImpl implements " + converClass(tableName)
					+ "Service {");
			newLine(sb, "}");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String mapper(String tableName) {
		try {
			StringBuffer sb = new StringBuffer();
			newLine(sb, packages("mapper"));
			newLine(sb, "import org.springframework.stereotype.Repository;");
			newLine(sb, "import " + projectPackage + ".basic.common.generic.mapper.BaseMapper;");
			newLine(sb, "import " + generatePackage + ".domain." + converClass(tableName) + ";");
			newLine(sb, "@Repository");
			newLine(sb, "public interface " + converClass(tableName) + "Mapper extends BaseMapper<"
					+ converClass(tableName) + ">{");
			newLine(sb, "}");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private String packages(String childpack) {
		return String.format("package %s.%s;\r\n", generatePackage, childpack);
	}

	private StringBuffer imports() {
		StringBuffer sb = new StringBuffer();
		newLine(sb, "import java.io.Serializable;");
		newLine(sb, "import javax.persistence.Column;");
		newLine(sb, "import javax.persistence.Id;");
		newLine(sb, "import javax.persistence.Table;");
		return sb;
	}

	private StringBuffer classes(String tableName, String commment) {
		StringBuffer sb = new StringBuffer();
		newLine(sb, "/***");
		newLine(sb, "* " + dateTime.format(new Date()));
		newLine(sb, String.format("* @Author %s", generateAuthor));
		newLine(sb, "* ");
		newLine(sb, "* @Deprecated " + commment);
		newLine(sb, "*/");
		newLine(sb, "");
		newLine(sb, "@Table(name = \"" + tableName + "\")");
		newLine(sb, "public class " + converClass(tableName) + " implements Serializable {");
		newLine(sb, "private static final long serialVersionUID = 1L;");
		return sb;
	}

	private StringBuffer fields(StringBuffer sb, String fieldName, String type, String comment) {
		String newName = converProperty(fieldName);
		newLine(sb, "@Column(name=\"`" + fieldName + "`\")");
		newLine(sb, "private " + type + " " + newName + "; //" + comment);
		return sb;
	}

	private StringBuffer props(StringBuffer sb, String fieldName, String type) {
		String newName = converProperty(fieldName);
		newLine(sb, "public " + type + " " + converProperty("get_" + fieldName) + "(){");
		newLine(sb, "return " + newName + ";");
		newLine(sb, "}");

		newLine(sb, "public void " + converProperty("set_" + newName) + "(" + type + " $" + newName + "){");
		newLine(sb, "this." + newName + "=$" + newName + ";");
		newLine(sb, "}");
		return sb;
	}

	private void newLine(StringBuffer sbuf, String line) {
		sbuf.append(line);
		sbuf.append("\r\n");
	}

	private String converProperty(String name) {
		StringBuffer sbuf = new StringBuffer();
		String[] names = name.split("_");
		for (int i = 0; i < names.length; i++) {
			if (i > 0) {
				sbuf.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));
			} else {
				sbuf.append(names[i]);
			}
		}
		return sbuf.toString();
	}

	private String converClass(String name) {
		StringBuffer sbuf = new StringBuffer();
		String[] names = name.split("_");
		if (names.length == 1) {
			sbuf.append(names[0]);
		} else {
			for (int i = 0; i < names.length; i++) {
				sbuf.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));
			}
		}
		return sbuf.toString();
	}

	public void write(String filePath, String fileContent) throws Exception {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		byte[] bytes = fileContent.getBytes("UTF-8");
		fos.write(bytes, 0, bytes.length);
		fos.close();
	}
}
