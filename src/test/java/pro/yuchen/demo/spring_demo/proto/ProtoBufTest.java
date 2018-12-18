package pro.yuchen.demo.spring_demo.proto;


import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import org.junit.Test;
import pro.yuchen.demo.spring_demo.proto.Protos.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProtoBufTest {

	@Test
	public void testProtoBuf() throws Exception {
		Person person = Person.newBuilder()
				.setId(1)
				.setName("Tom")
				.setEmail("yuchen352416@163.com")
				.addPhones(Person.PhoneNumber.newBuilder()
						.setNumber("18733775865")
						.setType(Person.PhoneType.MOBILE))
				.build();


		// ProtoBuf -> JSON
		String jsonFormat = JsonFormat.printToString(person);
		System.out.println(jsonFormat);

		// JSON -> ProtoBuf
		String json = "{\"name\": \"Tom\", \"id\": 1, \"email\": \"yuchen352416@163.com\", \"phones\": [{\"number\": \"18733775865\", \"type\": 1 }, {\"number\": \"13463170232\", \"type\": 0 } ] }";
		Message.Builder message = Person.newBuilder();
		JsonFormat.merge(json, message);
		System.out.println(message.build());

		byte[] bytes = person.toByteArray(); // 序列化
		Person result = Person.parseFrom(bytes); // 反序列化

		// 遍历输出
		for (Iterator<Map.Entry<FieldDescriptor, Object>> iter = result.getAllFields().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<FieldDescriptor, Object> field = iter.next();
			System.out.println("Number: "  + field.getKey().getNumber());
			System.out.println("Type: "  + field.getKey().getType());
			System.out.println("Name: "  + field.getKey().getName());
			System.out.println("FullName: "  + field.getKey().getFullName());
			System.out.println("FileName: "  + field.getKey().getFile().getName());
			printFieldValue(field);
			System.out.println("-------------------------------------------");
			System.out.println();
		}
	}

	private void printFieldValue(Map.Entry<FieldDescriptor, Object> field) {
		switch (field.getKey().getType()){
			case INT32:
			case INT64:
			case SINT32:
			case SINT64:
			case SFIXED32:
			case SFIXED64:
			case FLOAT:
			case DOUBLE:
			case BOOL:
				// Good old toString() does what we want for these types.
				System.out.println("Value: " + field.getValue().toString());
				break;

			case UINT32:
			case FIXED32:
				System.out.println("Value: " + (Integer) field.getValue());
				break;

			case UINT64:
			case FIXED64:
				System.out.println("Value: " + (Long) field.getValue());
				break;

			case STRING: {
				System.out.println("Value: " + (String) field.getValue());
				break;
			}

			case BYTES: {
				System.out.println("Value: " + (ByteString) field.getValue());
				break;
			}

			case ENUM: {
				System.out.println("Value: " + ((Descriptors.EnumValueDescriptor) field.getValue()).getName());
				break;
			}

			case MESSAGE:
			case GROUP:
				System.out.println("Size: " + ((List<?>) field.getValue()).size());
				System.out.println("Value: [");
				List<?> list = (List<?>) field.getValue();
				for (Object o : list) {
					Message message = (Message) o;
					for (Iterator<Map.Entry<FieldDescriptor, Object>> iters = message.getAllFields().entrySet().iterator(); iters.hasNext();) {
						Map.Entry<FieldDescriptor, Object> iter = iters.next();
						System.out.println("\t-----Number: "  + iter.getKey().getNumber());
						System.out.println("\t-----Type: "  + iter.getKey().getType());
						System.out.println("\t-----Name: "  + iter.getKey().getName());
						System.out.println("\t-----FullName: "  + iter.getKey().getFullName());
						System.out.println("\t-----FileName: "  + iter.getKey().getFile().getName());
						System.out.print("\t-----");
						printFieldValue(iter);
						System.out.println("\t--------------------------------------------");
					}
					System.out.println();
				}
				System.out.println("]");
				break;
		}
	}

}
