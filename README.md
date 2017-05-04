# Protocolizer
**Zero-overhead Java <-> Protocol Buffer** automatizer, at roughly 133Kb, the library is very light and only works in development pahse to get a single contact point with Protocol Buffer.

-------------------------------------------------------------------

## Description
Write less and do more annotating POJO Java classes that represents a protocol buffer message and left Protocolizer works for you. It's very simple.

### Structure
![esquma](https://github.com/ogalera/protocolizer/blob/master/resources/esquema.png)

Note that this library only works at compile time, so your project will never experiment any overhead ad deployment.

![esquma](https://github.com/ogalera/protocolizer/blob/master/resources/intercanvi.png)

-------------------------------------------------------------------
## Examples

```java
@ProtoFileV2.File(pJavaName = "Example1")
public class Example1 {

    @ProtoFileV2.File.Message
    public static class Person {

        @ProtoFileV2.File.Message.Field
        private String name;

        @ProtoFileV2.File.Message.Field
        private int id;

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String email;

        @ProtoFileV2.File.Message.Field
        private List<PhoneNumber> phones;

        @ProtoFileV2.File.Enum
        public static enum PhoneType {
            MOBILE,
            HOME,
            WORK
        }

        @ProtoFileV2.File.Message
        public static class PhoneNumber {

            @ProtoFileV2.File.Message.Field
            private String number;

            @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
            private PhoneType type;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public PhoneType getType() {
                return type;
            }

            public void setType(PhoneType type) {
                this.type = type;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<PhoneNumber> getPhones() {
            return phones;
        }

        public void setPhones(List<PhoneNumber> phones) {
            this.phones = phones;
        }

    }

    @ProtoFileV2.File.Message
    public static class AddressBook {

        @ProtoFileV2.File.Message.Field
        private List<Person> people;

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> people) {
            this.people = people;
        }
    }
}
```

Generated Google Protocol Buffer message

```proto
option java_package = "cat.ogasoft.protocolizer.messages";

option java_outer_classname = "Example1";


message Person {
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
    required PhoneNumber phones = 4;
    message PhoneNumber {
        required string number = 1;
        optional PhoneType type = 2;
    }
    enum PhoneType {
        MOBILE = 0;
        HOME = 1;
        WORK = 2;
    }
}

message AddressBook {
    required Person people = 1;
}
```

-------------------------------------------------------------------
## Installation
You only need **four very simple steps**.
1. Download **Google Protocol Buffer** and configure it in your IDE.
2. Download **Protocolizer.jar** and add it at your class path.
3. Configure the annotation processor (**cat.ogasoft.protocolizer.processor.ProtoFileProcessorV2**) in your IDE or classpath.
4. Done.