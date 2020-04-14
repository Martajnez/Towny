package com.palmergames.bukkit.towny.database.dbHandlers;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.database.handler.LoadContext;
import com.palmergames.bukkit.towny.database.handler.SQLData;
import com.palmergames.bukkit.towny.database.handler.SaveContext;
import com.palmergames.bukkit.towny.database.handler.SerializationHandler;
import com.palmergames.bukkit.towny.db.TownyFlatFileSource;
import com.palmergames.bukkit.towny.object.Town;

import java.util.UUID;

public class TownHandler implements SerializationHandler<Town> {
	@Override
	public Town loadString(LoadContext context, String str) {
		UUID townID;
		try {
			townID = UUID.fromString(str);
		} catch (Exception e) {
			TownyMessaging.sendErrorMsg(e.getMessage());
			return null;
		}
		
		return ((TownyFlatFileSource)TownyUniverse.getInstance().getDataSource()).getDatabaseHandler().getTown(townID);
	}

	@Override
	public Town loadSQL(LoadContext context, Object result) {
		return null;
	}

	@Override
	public String getFileString(SaveContext context, Town obj) {
		return obj.getUniqueIdentifier().toString();
	}

	@Override
	public SQLData getSQL(SaveContext context, Town obj) {
		return null;
	}
}
