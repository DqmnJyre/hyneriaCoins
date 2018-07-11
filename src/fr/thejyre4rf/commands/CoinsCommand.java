package fr.thejyre4rf.commands;

import fr.thejyre4rf.api.Coins;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor{

    public void sendHelp(CommandSender sender){
        if(sender.hasPermission("coins.mod")){
            sender.sendMessage("§7-------- §6HELP §7--------");
            if(sender.hasPermission("coins.admin")) {
                sender.sendMessage("§7/§acoins add <pseudo> <nombre>");
                sender.sendMessage("§7/§acoins remove <pseudo> <nombre>");
                sender.sendMessage("§7/§acoins set <pseudo> <nombre>");
            }
            sender.sendMessage("§7/§acoins see <pseudo>");
        }else{
            sender.sendMessage("§6[§7COINS§6] §a/coins!");
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String lb, String[] args){
        if(args.length == 0){
            if(sender.hasPermission("coins.mod")){
                sender.sendMessage("§7-------- §6HELP §7--------");
                if(sender.hasPermission("coins.admin")) {
                    sender.sendMessage("§7/§acoins add <pseudo> <nombre>");
                    sender.sendMessage("§7/§acoins remove <pseudo> <nombre>");
                    sender.sendMessage("§7/§acoins set <pseudo> <nombre>");
                }
                sender.sendMessage("§7/§acoins see <pseudo>");
            }else{
                if(!(sender instanceof Player)){
                    return false;
                }
                Player p = ((Player) sender).getPlayer();
                Coins c = new Coins(p);
                p.sendMessage("§6[§7COINS§6] §aVous avez §c" + c.get() + " §aCoins");
            }
            return false;
        }
        if(args.length < 2 || args.length > 4){
            sendHelp(sender);
            return false;
        }
        if(args.length == 2){
            String arg = args[0];
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
            Coins c = new Coins(p);
            if(sender.hasPermission("coins.mod") || sender.hasPermission("coins.admin")){
                if(arg.equalsIgnoreCase("see")){ sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà §c " + c.get() + " §aCoins"); return true; }
                else{ sendHelp(sender); }
            }
            else { sendHelp(sender); }
        }
        if(args.length == 3){
            String arg = args[0];
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[1]);
            Integer coin = Integer.parseInt(args[2]);
            Coins c = new Coins(p);
            if(sender.hasPermission("coins.admin")){
                if(arg.equalsIgnoreCase("add")){
                    if(p.isOnline()){
                        Player ps = p.getPlayer();
                        c.add(coin);
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                        ps.sendMessage("§6[§7COINS§6] §aVous avez désormais §c " + c.get() + " §aCoins");
                    }
                    else{
                        c.add(coin);
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                    }
                }
                if(arg.equalsIgnoreCase("remove")){
                    if(p.isOnline()){
                        c.remove(coin);
                        Player ps = p.getPlayer();
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                        ps.sendMessage("§6[§7COINS§6] §aVous avez désormais §c " + c.get() + " §aCoins");
                    }
                    else{
                        c.remove(coin);
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                    }
                }
                if(arg.equalsIgnoreCase("set")){
                    if(p.isOnline()){
                        c.set(coin);
                        Player ps = p.getPlayer();
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                        ps.sendMessage("§6[§7COINS§6] §aVous avez désormais §c " + c.get() + " §aCoins");
                    }
                    else{
                        c.set(coin);
                        sender.sendMessage("§6[§7COINS§6] §aLe joueur §c" + p.getName() + " §aà désormais §c " + c.get() + " §aCoins");
                    }
                }

            }
            else { sendHelp(sender); }
        }else { sendHelp(sender); }

        return false;
    }

}
