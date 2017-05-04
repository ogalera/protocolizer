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
package cat.ogasoft.protocolizer;

/**
 * @author Oscar Galera i Alfaro
 *
 * @brief Interface for those class that can serialize a Java class to a Protocol Buffer message.
 *
 * @param <T> The java type which will be serialized.
 */
public interface SerializerProtoWorker<T> {

    /**
    * @pre target has been instantiated.
    * @post the serialization process has been started.
    * @param target object to serialize.
    */
    public void work(T target);

    /**
    * @pre work has been assigned.
    * @post actual thread is paused until the serializer process ends.
    * @return raw representation of the Protocol buffer message.
    */
    public byte[] waitUntilEnd() throws InterruptedException;
}
