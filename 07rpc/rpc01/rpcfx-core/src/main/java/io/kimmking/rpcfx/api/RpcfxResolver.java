package io.kimmking.rpcfx.api;

public interface RpcfxResolver {

    Class<?> resolve(String serviceClass);

}
