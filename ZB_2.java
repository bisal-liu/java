package com.bisal.zb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ZB_2 {
	public static void main(String[] args) {
		try {
			JsonParser parser = new JsonParser(); // 创建JSON解析器
			JsonObject object = (JsonObject) parser.parse(new FileReader(
					"file/zhiban.log")); // 创建JsonObject对象
			JsonArray array = object.get("dutylist").getAsJsonArray(); // 得到为json的数组
			String dName = "";
			String dPerson = "";
			String dEmail = "";
			String dPhone = "";
			String dDate = "";
			List<ZBObject> zbObjectList = new ArrayList<ZBObject>();
			for (int i = 0; i < array.size(); i++) {
				JsonObject subObject = array.get(i).getAsJsonObject();
				dName = subObject.get("dName").getAsString();
				dPerson = subObject.get("dPerson").getAsString();
				dPhone = subObject.get("dPhone").getAsString();
				dEmail = subObject.get("dEmail").getAsString();
				dDate = subObject.get("startDate").getAsString()
						.substring(0, 10);
				ZBObject zbObject = new ZBObject();
				zbObject.setDName(dName);
				zbObject.setDPerson(dPerson);
				zbObject.setDEmail(dEmail);
				zbObject.setDPhone(dPhone);
				zbObject.setDDate(dDate);
				zbObjectList.add(zbObject);
			}
			FileWriter writer = new FileWriter("file/output2.txt");
			Collections.sort(zbObjectList);
			for (ZBObject zbo : zbObjectList) {
				writer.write("值班项=[" + zbo.getDName() + "] 日期=[" + zbo.getDDate()
						+ "] 值班人=[" + zbo.getDPerson() + "] 邮箱=[" + zbo.getDEmail()
						+ "] 电话=[" + zbo.getDPhone() + "]\n");
			}
			writer.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
