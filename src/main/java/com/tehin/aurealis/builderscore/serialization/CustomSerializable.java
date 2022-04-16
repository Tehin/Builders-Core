package com.tehin.aurealis.builderscore.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CustomSerializable<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013562225615954759L;

	public byte[] serializeClass(T clazz)  {
		ByteArrayOutputStream baus = new ByteArrayOutputStream();
		try (ObjectOutputStream out = new ObjectOutputStream(baus)) {
			out.writeObject(clazz);
			return baus.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (baus != null) {
				try {
					baus.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public T deserializeClass(byte[] byteArray) {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		try (ObjectInputStream ois = new ObjectInputStream(bais)) {
			@SuppressWarnings("unchecked")
			T object = (T) ois.readObject();
			return object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
