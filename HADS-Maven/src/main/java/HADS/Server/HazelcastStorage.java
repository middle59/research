package HADS.Server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.config.*;

import java.util.Map;

public class HazelcastStorage extends StorageConnector
{

	public HazelcastInstance hazelcastInstance;
	private int port = 5701;

	public HazelcastStorage()
	{
		initialize();
	}

	public boolean initialize()
	{
		try {
			System.out.println("Initializing Hazelcast Cluster...");
			Config cfg = new Config();
			cfg.setProperty("hazelcast.health.monitoring.level", "OFF");
			System.out.println("Hazelcast: Config set.");
			hazelcastInstance = Hazelcast.newHazelcastInstance(cfg);
			System.out.println("Hazelcast: A node has joined the cluster.");
			System.out.println("Hazelcast: Current Cluster Size is " +
				hazelcastInstance.getCluster().getMembers().size() + ".");
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public Object put(Object K, Object V)
	{
		Map<Object, Object> hazelcastMap = hazelcastInstance.getMap("map");
		return hazelcastMap.put(K, V);
	}

	public Object get(Object K)
	{
		Map<Object, Object> hazelcastMap = hazelcastInstance.getMap("map");
		return hazelcastMap.get(K);
	}

}
