import java.util.HashMap;
import java.util.Map;

class ARPCache {
    private Map<String, String> cache;

    public ARPCache() {
        this.cache = new HashMap<>();
    }

    public void addToCache(String ipAddress, String macAddress) {
        cache.put(ipAddress, macAddress);
    }

    public String getMacAddress(String ipAddress) {
        return cache.get(ipAddress);
    }
}

class ARPSimulator {
    private ARPCache arpCache;

    public ARPSimulator(ARPCache arpCache) {
        this.arpCache = arpCache;
    }

    public void simulateARP(String ipAddress) {
        String macAddress = getMacAddressFromARP(ipAddress);
        System.out.println("MAC Address for IP " + ipAddress + ": " + macAddress);
    }

    private String getMacAddressFromARP(String ipAddress) {
        String macAddress = arpCache.getMacAddress(ipAddress);

        if (macAddress == null) {
            macAddress = generateRandomMAC(); // Simulating actual ARP request to get MAC address
            arpCache.addToCache(ipAddress, macAddress);
        }

        return macAddress;
    }

    private String generateRandomMAC() {
        // Simulating the process of obtaining the MAC address from the network
        // In a real scenario, this would involve network communication to retrieve the
        // MAC address
        return "00:1A:2B:3C:4D:5E";
    }
}

public class ARPSimulation {
    public static void main(String[] args) {
        ARPCache arpCache = new ARPCache();
        ARPSimulator arpSimulator = new ARPSimulator(arpCache);

        // Simulate ARP requests
        arpSimulator.simulateARP("192.168.1.1");
        arpSimulator.simulateARP("192.168.1.2");
        arpSimulator.simulateARP("192.168.1.1"); // This time it should be retrieved from the cache
    }
}
