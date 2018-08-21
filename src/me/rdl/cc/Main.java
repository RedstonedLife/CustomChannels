package me.rdl.cc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.rdl.cc.commands.ChannelSelect;
import me.rdl.cc.commands.CustomChannels;
import me.rdl.cc.events.ChatEvent;
import me.rdl.cc.events.JoinEvent;


public class Main extends JavaPlugin {
	
	// YouTube and Twitch
	
	File configFile;
    File playersFile;

    FileConfiguration config;
    FileConfiguration players;
    	
	public void onEnable() 
	{
		// Commands
		new ChannelSelect(this);
		new CustomChannels(this);
		
		//Events
		new ChatEvent(this);
		new JoinEvent(this);
		
		// Command Events
		
		
		/*
		YML Loader
		*/
		
		configFile = new File(getDataFolder(), "config.yml");
        playersFile = new File(getDataFolder(), "players.yml");
        
        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        config = new YamlConfiguration();
        players = new YamlConfiguration();
        loadYamls();
		
	}
	
	private void firstRun() throws Exception {
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();
            copy(getResource("config.yml"), configFile);
        }
        if(!playersFile.exists()){
            playersFile.getParentFile().mkdirs();
            copy(getResource("players.yml"), playersFile);
        }
    }
    
    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadYamls() {
        try {
            config.load(configFile);
            players.load(playersFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveYamls() {
        try {
            config.save(configFile);
            players.save(playersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // YAMLS
    
    public FileConfiguration getplayers() {
    	
        return players;
    }
    public FileConfiguration getconfigs() {
    	return config;
    }
    

}
