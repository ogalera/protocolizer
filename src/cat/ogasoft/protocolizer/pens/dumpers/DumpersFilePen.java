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
package cat.ogasoft.protocolizer.pens.dumpers;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 30, 2017 [3:27:18 PM]
 *
 * @brief Pen container for dumpers (serializer, deserializer).
 */
public class DumpersFilePen {

    private DumperFilePen serializer; //<Serializer pen.
    private DumperFilePen deserializer; //<Deserializer pen.

    public DumperFilePen getSerializer() {
        return serializer;
    }

    public void setSerializer(DumperFilePen serializer) {
        this.serializer = serializer;
    }

    public DumperFilePen getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(DumperFilePen deserializer) {
        this.deserializer = deserializer;
    }

    public boolean hasSerializer() {
        return serializer != null;
    }

    public boolean hasDeserializer() {
        return deserializer != null;
    }

}
