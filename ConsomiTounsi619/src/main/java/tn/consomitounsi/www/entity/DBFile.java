package tn.consomitounsi.www.entity;

import javax.persistence.Id;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class DBFile implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -601832586093965264L;

	@Id
    private Long name;

    private String fileType;

    @Lob
    private byte[] data;

    
	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public DBFile(Long name, String fileType, byte[] data) {
		this.name=name;
		this.fileType = fileType;
		this.data = data;
	}

	public DBFile() {
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DBFile other = (DBFile) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		return true;
	}

}