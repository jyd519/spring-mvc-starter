package ata.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jiyongdong on 15/5/13.
 */
public class SerializeUtil {

    public static String toString(Object object) throws Exception {
        // serialize the object
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(object);
        so.flush();
        return org.apache.commons.codec.binary.Base64.encodeBase64String(bo.toByteArray());

    }

    public static Object fromString(String str) throws Exception {
        // deserialize the object
        byte b[] = org.apache.commons.codec.binary.Base64.decodeBase64(str);
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        return si.readObject();
    }
}
