import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.junit.Test;

/**
 * Created by peramdy on 2017/12/28.
 */
public class ApolloTest {


    /**
     * -Dapp.id=svr-0001 -Denv=DEV -Ddev_meta=http://192.168.136.131:8080
     */
    @Test
    public void get() {

        ConfigChangeListener changeListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println("Change - key: " + change.getPropertyName() +
                            ", oldValue: " + change.getOldValue() +
                            ", newValue: " + change.getNewValue() +
                            ", changeType: " + change.getChangeType()
                    );
                }
            }
        };

        Config config = ConfigService.getAppConfig();
        config.addChangeListener(changeListener);
        String value = config.getProperty("huahua", "nihao");
        System.out.println(value);

    }

}
