package net.akashaverse.pixelmonhome.service;


import net.akashaverse.pixelmonhome.config.PHConfig;
import net.akashaverse.pixelmonhome.util.PHLogger;
import org.jetbrains.annotations.Nullable;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public final class HomeApiClient {
    private static final HttpClient CLIENT = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();


    public static @Nullable String deposit(String playerUuid, String serverId, String encodedNbtBase64) {
        try {
            var req = HttpRequest.newBuilder(URI.create(PHConfig.API_BASE.get() + "/v1/deposit"))
                    .timeout(Duration.ofSeconds(8))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{" +
                            "\"player\":\"" + playerUuid + "\"," +
                            "\"server\":\"" + serverId + "\"," +
                            "\"nbt\":\"" + encodedNbtBase64 + "\"}"))
                    .build();
            var res = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) return res.body();
            PHLogger.LOG.warn("Deposit failed: status {} body {}", res.statusCode(), res.body());
        } catch (Exception e) { PHLogger.LOG.error("HTTP deposit error", e); }
        return null;
    }


    public static @Nullable String withdraw(String playerUuid, String entryId) {
        try {
            var req = HttpRequest.newBuilder(URI.create(PHConfig.API_BASE.get() + "/v1/withdraw/" + entryId))
                    .timeout(Duration.ofSeconds(8)).GET().build();
            var res = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 200) return res.body();
            PHLogger.LOG.warn("Withdraw failed: status {} body {}", res.statusCode(), res.body());
        } catch (Exception e) { PHLogger.LOG.error("HTTP withdraw error", e); }
        return null;
    }
}