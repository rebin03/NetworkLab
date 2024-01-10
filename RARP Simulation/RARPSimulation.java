import java.util.HashMap;
import java.util.Map;

class RARPCache {
    private Map<String, String> cache;

    public RARPCache() {
        this.cache = new HashMap<>();
    }

    public void addToCache(String macAddress, String ipAddress) {
        cache.put(macAddress, ipAddress);
    }

    public String getIPAddress(String macAddress) {
        return cache.get(macAddress);
    }
}

class RARPSimulator {
    private RARPCache rarpCache;

    public RARPSimulator(RARPCache rarpCache) {
        this.rarpCache = rarpCache;
    }

    public void simulateRARP(String macAddress) {
        String ipAddress = getIPAddressFromRARP(macAddress);
        System.out.println("IP Address for MAC " + macAddress + ": " + ipAddress);
    }

    private String getIPAddressFromRARP(String macAddress) {
        String ipAddress = rarpCache.getIPAddress(macAddress);

        if (ipAddress == null) {
            ipAddress = generateRandomIP(); // Simulating actual RARP request to get IP address
            rarpCache.addToCache(macAddress, ipAddress);
        }

        return ipAddress;
    }

    private String generateRandomIP() {
        // Simulating the process of obtaining the IP address from the network
        // In a real scenario, this would involve network communication to retrieve the
        // IP address
        return "192.168.1.1";
    }
}

public class RARPSimulation {
    public static void main(String[] args) {
        RARPCache rarpCache = new RARPCache();
        RARPSimulator rarpSimulator = new RARPSimulator(rarpCache);

        // Simulate RARP requests
        rarpSimulator.simulateRARP("00:1A:2B:3C:4D:5E");
        rarpSimulator.simulateRARP("00:1A:2B:3C:4D:5F");
        rarpSimulator.simulateRARP("00:1A:2B:3C:4D:5E"); // This time it should be retrieved from the cache
    }
}
