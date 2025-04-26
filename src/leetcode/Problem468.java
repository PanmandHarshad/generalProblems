package leetcode;

public class Problem468 {

    public static void main(String[] args) {
        String[] testInputs = {
                "172.16.254.1",   // IPv4
                "2001:0db8:85a3:0:0:8A2E:0370:7334", // IPv6
                "256.256.256.256", // Neither
                "1e1.4.5.6",       // Neither
                "2001:db8:85a3::8a2e:370:7334", // Neither
                "192.0.0.1",       // IPv4
                "02001:0db8:85a3:0000:0000:8a2e:0370:7334" // Neither
        };

        for (String ip : testInputs) {
            String result = validIPAddress(ip);
            System.out.println("Input: " + ip + " --> Output: " + result);
        }
    }


    static public String validIPAddress(String queryIP) {
        // "1.1.1.1.".split("\\.", -1) → ["1", "1", "1", "1", ""]
        String[] IPv4 = queryIP.split("\\.", -1);
        if (IPv4.length == 4)
            return isValidIPv4(IPv4) ? "IPv4" : "Neither";

        String[] IPv6 = queryIP.split(":", -1);
        if (IPv6.length == 8)
            return isValidIPv6(IPv6) ? "IPv6" : "Neither";

        return "Neither";
    }

    static private boolean isValidIPv4(String[] IP) {

        for (String segment : IP) {
            if (segment.isEmpty() || segment.length() > 3)
                return false;
            if (segment.charAt(0) == '0' && segment.length() != 1)
                return false;

            for (char c : segment.toCharArray()) {
                if (!Character.isDigit(c))
                    return false;
            }

            int num = Integer.parseInt(segment);
            if (num < 0 || num > 255)
                return false;
        }

        return true;
    }

    static private boolean isValidIPv6(String[] IP) {
        for (String segment : IP) {
            if (segment.isEmpty() || segment.length() > 4)
                return false;

            for (char c : segment.toCharArray()) {
                if (!isHexDigit(c)) return false;
            }
        }

        return true;
    }

    static private boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'f') ||
                (c >= 'A' && c <= 'F');
    }
}
