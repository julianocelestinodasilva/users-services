package julianocelestino.usersservices;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.logging.Logger;

/**
 * Created by juliano on 29/12/17.
 */
public class LocationRepository {

    private static final String ADDRESS_NOT_FOUND = "AddressNotFound";

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private DatabaseReader dbReader;
    private ResourceLoader resourceLoader;

    public LocationRepository(ResourceLoader rl) {
        resourceLoader = rl;
        try {
            Resource resource = resourceLoader.getResource("classpath:GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();
            dbReader = new DatabaseReader
                    .Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't be possible load the dbGeoCity " + e);
        }
    }

    public String findCity (String ip)  {
        CityResponse response;
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            response = dbReader.city(ipAddress);
            return response.getCity().getName();
        } catch (AddressNotFoundException ae)    {
            logger.info(ae.getMessage() + " Returning city=" + ADDRESS_NOT_FOUND);
            return ADDRESS_NOT_FOUND;
        } catch (IOException |GeoIp2Exception e) {
            throw new RuntimeException("Couldn't be possible find the City" + e);
        }
    }
}
