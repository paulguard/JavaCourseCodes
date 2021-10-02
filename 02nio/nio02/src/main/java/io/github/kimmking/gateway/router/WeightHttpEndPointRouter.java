package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class WeightHttpEndPointRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        throw new RuntimeException("Unsupported Method");
    }

    @Override
    public String route(List<String> endpoints, List<Integer> weight) {

        Random random = new Random(System.currentTimeMillis());
        int randomInt = random.nextInt(100);

        for (int i = 0; i < weight.size(); i++) {

            if (i == weight.size() - 1) {
                return endpoints.get(i);
            }

            Integer curWeight = weight.get(i);
            if (randomInt > curWeight) {
                continue;
            }

            return endpoints.get(i);
        }

        return endpoints.get(0);
    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
        System.out.println(random.nextInt(100));
    }
}
