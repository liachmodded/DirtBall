package info.tritusk.dirtball.impl.bukkit;

import info.tritusk.dirtball.Dirtball;
import info.tritusk.dirtball.DirtballConstants;
import info.tritusk.dirtball.DirtballPlatform;
import info.tritusk.dirtball.DirtballUnimplementedException;
import info.tritusk.dirtball.impl.bukkit.boss.DirtballBossBar;
import info.tritusk.dirtball.impl.bukkit.entity.DirtballPlayer;
import info.tritusk.dirtball.impl.bukkit.item.DirtballItemFactory;
import info.tritusk.dirtball.impl.bukkit.scoreboard.DirtballScoreboardManager;
import info.tritusk.dirtball.impl.bukkit.util.DirtballServerIcon;
import info.tritusk.dirtball.util.DirtballObjectsConverter;
import org.bukkit.BanList;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.UnsafeValues;
import org.bukkit.Warning;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.advancement.Advancement;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.boss.ServerBossBar;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.network.status.Favicon;
import org.spongepowered.api.network.status.StatusResponse;
import org.spongepowered.api.service.ban.BanService;
import org.spongepowered.api.service.user.UserStorageService;
import org.spongepowered.api.service.whitelist.WhitelistService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;
import org.spongepowered.api.util.ban.Ban;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.storage.WorldProperties;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * DirtballServer is an implementation of {@linkplain org.bukkit.Server Bukkit Server}, with an instance
 * of {@linkplain org.spongepowered.api.Server Sponge Server} in its backend.
 * <br>
 * This implementation has name of {@link DirtballConstants#BUKKIT_IMPL_NAME}.
 * <br>
 * This implementation has version of {@link DirtballConstants#BUKKIT_IMPL_VERSION}, using
 * Bukkit/Spigot API with version number of {@link DirtballConstants#BUKKIT_API_VERSION}.
 * <br>
 * <p>
 * Assumptions that are based on analysis of SpongeAPI are made in order to implements several concepts that
 * present in Bukkit but not observed in Sponge.  They are listed as below:
 * </p>
 * <ul>
 *     <li>
 *         Assume {@linkplain Server#getDefaultWorld() the default world} represents the world that is specified
 *         by <code>server.properties</code>. This enables the appropriate implementation of following methods:
 *         <ul>
 *             <li>{@link #getWorldType()}</li>
 *             <li></li>
 *         </ul>
 *     </li>
 *     <li>
 *
 *     </li>
 * </ul>
 *
 */
public class DirtballServer implements org.bukkit.Server {

    private final Server spongeServer;

    public DirtballServer(Server spongeServer) {
        this.spongeServer = spongeServer;
    }

    @Override
    public String getName() {
        return DirtballConstants.BUKKIT_IMPL_NAME;
    }

    @Override
    public String getVersion() {
        return DirtballConstants.BUKKIT_IMPL_VERSION;
    }

    @Override
    public String getBukkitVersion() {
        return DirtballConstants.BUKKIT_API_VERSION;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return spongeServer.getOnlinePlayers().stream().map(DirtballPlayer::new).collect(Collectors.toList());
    }

    @Override
    public int getMaxPlayers() {
        return spongeServer.getMaxPlayers();
    }

    @Override
    public int getPort() {
        return spongeServer.getBoundAddress().orElseThrow(IllegalStateException::new).getPort();
    }

    @Override
    public int getViewDistance() {
        return 0;
    }

    @Override
    public String getIp() {
        return spongeServer.getBoundAddress().map(InetSocketAddress::toString).orElse("");
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public String getServerId() {
        return null;
    }

    @Override
    public String getWorldType() {
        return spongeServer.getDefaultWorld().map(WorldProperties::getGeneratorType).map(GeneratorType::toString).orElse("DEFAULT");
    }

    @Override
    public boolean getGenerateStructures() {
        return spongeServer.getDefaultWorld().filter(WorldProperties::usesMapFeatures).isPresent();
    }

    @Override
    public boolean getAllowEnd() {
        throw new DirtballUnimplementedException();
    }

    @Override
    public boolean getAllowNether() {
        throw new DirtballUnimplementedException();
    }

    @Override
    public boolean hasWhitelist() {
        return Sponge.getServiceManager().provide(WhitelistService.class).isPresent();
    }

    @Override
    public void setWhitelist(boolean value) {
        Dirtball.platform.getLogger().error("Whitelist is not implemented in Dirtball yet");
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        UserStorageService userStorage = Sponge.getServiceManager().provide(UserStorageService.class).orElseThrow(Error::new);
        return Sponge.getServiceManager().provide(WhitelistService.class)
                .map(WhitelistService::getWhitelistedProfiles)
                .map(players -> players.stream()
                        .map(userStorage::get)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map(DirtballOfflinePlayer::new)
                        .collect(Collectors.<OfflinePlayer>toSet()))
                .orElse(Collections.emptySet());
    }

    @Override
    public void reloadWhitelist() {
        Dirtball.platform.getLogger().error("Whitelist is not implemented in Dirtball yet");
        Sponge.getServiceManager().provide(WhitelistService.class).ifPresent(whitelist -> {

        });
    }

    @Override
    public int broadcastMessage(String message) {
        MessageChannel channel = MessageChannel.TO_PLAYERS;
        channel.send(Text.of(message));
        return channel.getMembers().size();
    }

    @Override
    public String getUpdateFolder() {
        // Fixed path
        return "updating";
    }

    @Override
    public File getUpdateFolderFile() {
        return new File(DirtballPlatform.bukkitPluginsDirectory, getUpdateFolder());
    }

    @Override
    public long getConnectionThrottle() {
        return 0L;
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;
    }

    @Override
    public Player getPlayer(String name) {
        return null;
    }

    @Override
    public Player getPlayerExact(String name) {
        return null;
    }

    @Override
    public List<Player> matchPlayer(String name) {
        return null;
    }

    @Override
    public Player getPlayer(UUID id) {
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    @Override
    public BukkitScheduler getScheduler() {
        return null;
    }

    @Override
    public ServicesManager getServicesManager() {
        return null;
    }

    @Override
    public List<World> getWorlds() {
        return null;
    }

    @Override
    public World createWorld(WorldCreator creator) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @implNote
     * By default, DirtballServer ignores the <code>save</code> parameter - it will always try
     * save the world to disk.
     */
    @Override
    public boolean unloadWorld(String name, boolean save) {
        Optional<org.spongepowered.api.world.World> world = spongeServer.getWorld(name);
        return world.isPresent() && spongeServer.unloadWorld(world.get());
    }

    @Override
    public boolean unloadWorld(World world, boolean save) {
        return world instanceof DirtballWorld && spongeServer.unloadWorld(((DirtballWorld) world).getSpongeWorld());
    }

    @Override
    public World getWorld(String name) {
        return spongeServer.getWorld(name).map(DirtballWorld::new).orElse(null);
    }

    @Override
    public World getWorld(UUID uid) {
        return spongeServer.getWorld(uid).map(DirtballWorld::new).orElse(null);
    }

    @Deprecated
    @Override
    public MapView getMap(short id) {
        return null;
    }

    @Override
    public MapView createMap(World world) {
        return null;
    }

    @Override
    public void reload() {

    }

    @Override
    public void reloadData() {

    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        return null;
    }

    @Override
    public void savePlayers() {

    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
        return false;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        Dirtball.platform.getLogger().warn("Calling getRecipeFor(ItemStack) is not supported");
        return Collections.emptyList();
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        Dirtball.platform.getLogger().warn("Calling getRecipeIterator() is not supported");
        return Collections.emptyIterator();
    }

    @Override
    public void clearRecipes() {
        Dirtball.platform.getLogger().warn("Calling clearRecipes() is not supported");
    }

    @Override
    public void resetRecipes() {
        Dirtball.platform.getLogger().warn("Calling resetRecipes() is not supported");
    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        return Sponge.getCommandManager().getCommands().stream().collect(
                HashMap<String, String[]>::new,
                (map, command) -> map.put(command.getPrimaryAlias(), command.getAllAliases().toArray(new String[0])),
                Map::putAll
        );
    }

    @Override
    public int getSpawnRadius() {
        return 0;
    }

    @Override
    public void setSpawnRadius(int value) {

    }

    @Override
    public boolean getOnlineMode() {
        return spongeServer.getOnlineMode();
    }

    @Override
    public boolean getAllowFlight() {
        Dirtball.platform.getLogger().error("getAllowFlight() is not functional yet! Assuming flying is not allowed.");
        return false;
    }

    @Override
    public boolean isHardcore() {
        Optional<WorldProperties> worldProperties = spongeServer.getDefaultWorld();
        return worldProperties.isPresent() && worldProperties.get().isHardcore();
    }

    @Override
    public void shutdown() {
        spongeServer.shutdown();
    }

    @Override
    public int broadcast(String message, String permission) {
        MessageChannel channel = MessageChannel.permission(permission);
        channel.send(Text.of(message));
        return channel.getMembers().size();
    }

    @Deprecated
    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return Sponge.getServiceManager()
                .provide(UserStorageService.class)
                .orElseThrow(IllegalStateException::new)
                .get(name)
                .map(DirtballOfflinePlayer::new)
                .orElse(null);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        return Sponge.getServiceManager()
                .provide(UserStorageService.class)
                .orElseThrow(IllegalStateException::new)
                .get(id)
                .map(DirtballOfflinePlayer::new)
                .orElse(null);
    }

    @Override
    public Set<String> getIPBans() {
        return Sponge.getServiceManager()
                .provide(BanService.class)
                .orElseThrow(IllegalStateException::new)
                .getIpBans()
                .stream()
                .map(Ban.Ip::getAddress)
                .map(InetAddress::getHostAddress)
                .collect(Collectors.toSet());
    }

    @Override
    public void banIP(String address) {
        try {
            Sponge.getServiceManager()
                    .provide(BanService.class)
                    .orElseThrow(IllegalStateException::new)
                    .addBan(Ban.builder().address(InetAddress.getByName(address)).build());
        } catch (UnknownHostException e) {
            Dirtball.platform.getLogger().error("Failed on banning {}", address, e);
        }
    }

    @Override
    public void unbanIP(String address) {
        try {
            Sponge.getServiceManager()
                    .provide(BanService.class)
                    .orElseThrow(IllegalStateException::new)
                    .pardon(InetAddress.getByName(address));
        } catch (UnknownHostException e) {
            Dirtball.platform.getLogger().error("Failed on unbanning {}", address, e);
        }
    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        UserStorageService userStorage = Sponge.getServiceManager().provide(UserStorageService.class).orElseThrow(IllegalStateException::new);
        return Sponge.getServiceManager()
                .provide(BanService.class)
                .orElseThrow(IllegalStateException::new)
                .getProfileBans()
                .stream()
                .map(Ban.Profile::getProfile)
                .map(userStorage::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(User::getPlayer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(DirtballOfflinePlayer::new)
                .collect(Collectors.toSet());
    }

    @Override
    public BanList getBanList(BanList.Type type) {
        return null;
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        Dirtball.platform.getLogger().error("getOperators() is not fully functional on Dirtball platform! Assuming no one is op.");
        return Collections.emptySet();
    }

    @Override
    public GameMode getDefaultGameMode() {
        return spongeServer.getDefaultWorld()
                .map(WorldProperties::getGameMode)
                .map(DirtballObjectsConverter::fromSponge)
                .orElse(null);
    }

    @Override
    public void setDefaultGameMode(GameMode mode) {
        spongeServer.getDefaultWorld().ifPresent(worldProperties -> worldProperties.setGameMode(DirtballObjectsConverter.fromBukkit(mode)));
    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }

    @Override
    public File getWorldContainer() {
        return null;
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        UserStorageService service = Sponge.getServiceManager().provide(UserStorageService.class).orElseThrow(Error::new);
        return service.getAll().stream().map(service::get).filter(Optional::isPresent).map(Optional::get).map(DirtballOfflinePlayer::new).toArray(OfflinePlayer[]::new);
    }

    @Override
    public Messenger getMessenger() {
        return null;
    }

    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Merchant createMerchant(String title) {
        return null;
    }

    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    @Override
    public boolean isPrimaryThread() {
        return spongeServer.isMainThread();
    }

    @Override
    public String getMotd() {
        return TextSerializers.FORMATTING_CODE.serialize(spongeServer.getMotd());
    }

    @Override
    public String getShutdownMessage() {
        return "Server closed";
    }

    /**
     * {@inheritDoc}
     * @implNote
     * This implementation will always warn deprecated methods usage.
     * @return {@link Warning.WarningState#ON}
     */
    @Override
    public Warning.WarningState getWarningState() {
        return Warning.WarningState.ON;
    }

    /**
     * {@inheritDoc}
     * @implSpec
     * This implementation will return a singleton instance of {@link ItemFactory}.
     * @return The singleton instance of Dirtball implementation of ItemFactory
     */
    @Override
    public ItemFactory getItemFactory() {
        return DirtballItemFactory.INSTANCE;
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return DirtballScoreboardManager.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * @implNote
     * Current implementation is wrong; result of {@link StatusResponse#getFavicon()}
     * must be cached in order to implement this correctly.
     * @return null under all circumstances
     */
    @Override
    public CachedServerIcon getServerIcon() {
        Dirtball.platform.getLogger().error("getServerIcon has incorrect, dummy implementation and will always return null!");
        return null;
    }

    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
        Favicon favicon = Sponge.getRegistry().loadFavicon(file.toPath());
        return new DirtballServerIcon(favicon);
    }

    @Override
    public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception {
        Favicon favicon = Sponge.getRegistry().loadFavicon(image);
        return new DirtballServerIcon(favicon);
    }

    @Override
    public void setIdleTimeout(int threshold) {
        spongeServer.setPlayerIdleTimeout(threshold);
    }

    @Override
    public int getIdleTimeout() {
        return spongeServer.getPlayerIdleTimeout();
    }

    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        return null;
    }

    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        return new DirtballBossBar(ServerBossBar.builder().build());
    }

    @Override
    public Entity getEntity(UUID uuid) {
        return null;
    }

    @Override
    public Advancement getAdvancement(NamespacedKey key) {
        Dirtball.platform.getLogger().error("org.bukkit.Server::getAdvancement has not implemented yet");
        return null;
    }

    @Override
    public Iterator<Advancement> advancementIterator() {
        Dirtball.platform.getLogger().error("org.bukkit.Server::advancementIterator has not implemented yet");
        return Collections.emptyIterator();
    }

    @Override
    public UnsafeValues getUnsafe() {
        return DirtballConstants.USE_BUKKIT_UNSAFE_VALUES ? new DirtballUnsafeValuesAnarchy() : new DirtballUnsafeValuesLawful();
    }

    @Override
    public Spigot spigot() {
        return this.serverSpigot;
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }

    private final Spigot serverSpigot = new SpigotImpl();
    private final class SpigotImpl extends org.bukkit.Server.Spigot {}
}
