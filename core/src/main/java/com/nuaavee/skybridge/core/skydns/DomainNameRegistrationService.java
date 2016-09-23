package com.nuaavee.skybridge.core.skydns;

import net.anshulverma.skydns.SkydnsClient;
import net.anshulverma.skydns.SkydnsClientFactory;
import net.anshulverma.skydns.SkydnsHost;
import net.anshulverma.skydns.error.RemoteConnectionException;
import net.anshulverma.skydns.error.SerializationException;
import com.nuaavee.skybridge.core.ApplicationConfig;
import com.nuaavee.skybridge.core.error.DNSRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainNameRegistrationService {

  private static final int DEFAULT_TTL_MINUTES = 60; // 1 hour

  private SkydnsClient skydnsClient;

  @Autowired
  public DomainNameRegistrationService(ApplicationConfig config)
    throws RemoteConnectionException, SerializationException {

    skydnsClient = SkydnsClientFactory.newClient(config.getEtcdMachines().split(","));
  }

  public void register(String taskId, String appId, String host) throws DNSRegistrationException {
    SkydnsHost skydnsHost = buildSkydnsHost(host);
    try {
      skydnsClient.register(skydnsHost, appId, taskId);
    } catch (SerializationException | RemoteConnectionException e) {
      throw new DNSRegistrationException("unable to register " + skydnsHost, e);
    }
  }

  private SkydnsHost buildSkydnsHost(String host) {
    return SkydnsHost.builder()
                     .host(host)
                     .timeToLive(DEFAULT_TTL_MINUTES)
                     .build();
  }

  public void unregister(String taskId, String appId, String host) {

  }
}
