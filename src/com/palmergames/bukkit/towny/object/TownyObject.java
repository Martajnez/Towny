package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.object.metadata.CustomDataField;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public abstract class TownyObject implements Nameable, Saveable {
	private String name;
	private final UUID uniqueIdentifier;

	private transient HashSet<CustomDataField<?>> metadata = null;
	
	public TownyObject(UUID id) {
		this.uniqueIdentifier = id;
	}
	
	public TownyObject(UUID id, String name) {
		this(id);
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public List<String> getTreeString(int depth) {

		return new ArrayList<>();
	}

	public String getTreeDepth(int depth) {

		char[] fill = new char[depth * 4];
		Arrays.fill(fill, ' ');
		if (depth > 0) {
			fill[0] = '|';
			int offset = (depth - 1) * 4;
			fill[offset] = '+';
			fill[offset + 1] = '-';
			fill[offset + 2] = '-';
		}
		return new String(fill);
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Get the formatted name, usually replacing the "_" with a space.
	 * For example: <code>"Object_Name"</code> would be <code>"Object Name"</code>
	 * 
	 * @return The formatted name.
	 */
	public String getFormattedName() {
		return getName().replaceAll("_", " ");
	}

	public void addMetaData(CustomDataField<?> md) {
		if (getMetadata() == null)
			metadata = new HashSet<>();

		getMetadata().add(md);
	}

	public void removeMetaData(CustomDataField<?> md) {
		if (!hasMeta())
			return;

		getMetadata().remove(md);

		if (getMetadata().size() == 0)
			this.metadata = null;
	}

	public HashSet<CustomDataField<?>> getMetadata() {
		return metadata;
	}

	public boolean hasMeta() {
		return getMetadata() != null;
	}

	public void setMetadata(String str) {

		if (metadata == null)
			metadata = new HashSet<>();

		String[] objects = str.split(";");
		for (String object : objects) {
			metadata.add(CustomDataField.load(object));
		}
	}
	
	public abstract String getSavePath();

	@Override
	public final @NotNull UUID getUniqueIdentifier() {
		return uniqueIdentifier;
	}
}
