/*
 * Copyright 2017 Oscar Galera i Alfaro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   May 4, 2017 [8:55:01 PM]
 *
 * @brief Very simple Protocolizer example.
 */
@ProtoFileV2.File(pJavaName = "Example")
@ProtoFileV2.Dumpper
public class Example {

    @ProtoFileV2.File.Message
    public static class Person {

        @ProtoFileV2.File.Message.Field
        private String name;

        @ProtoFileV2.File.Message.Field
        private int id;

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String email;

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
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

    @ProtoFileV2.File.Message(parallel = true)
    public static class AddressBook {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Person> people;

        public List<Person> getPeople() {
            return people;
        }

        public void setPeople(List<Person> people) {
            this.people = people;
        }
    }
}
