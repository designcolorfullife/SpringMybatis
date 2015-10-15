import com.zhangwei.ibatis.model.User;
import com.zhangwei.ibatis.model.dao.UserDao;
import com.zhangwei.tools.PresureTestTools;
import com.zhangwei.tools.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;


public class Test {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    private static Logger log = LoggerFactory.getLogger(Test.class);
    private static UserDao userDao = (UserDao) applicationContext.getBean("userDao");
    static {
        try {
        } catch (Exception e) {
            // TODO: handle exception
            log.error("初始化失败" + e);
        }
    }
    private static int count = 0;

    public void RedisTest(){
            final String[] id = {"268810000021199931873","268810000020627772095","268810000020627778158","268810000020627779160",
                    "268810000021043864735","268810000021070898844","268810000021132713344","268810000021179887842",
                    "268810000021179930289","268810000021199684409","268810000021199686429","268810000021199687439",
                    "2688100000211996874390","268810000021199688441","268810000021199689451","268810000021199690460",
                    "268810000021199691470","268810000021199694509","268810000021199695519","268810000021199696529",
                    "268810000021199697539","268810000021199698541","268810000021199729856","268810000021199730863",
                    "268810000021199731873","268810000021199732881","268810000021199733891","268810000021199734904",
                    "268810000021199735914","268810000021199736924","268810000021199737934","268810000021199738946",
                    "268810000021199739956","268810000021199740961","268810000021199741971","268810000021199742987",
                    "268810000021199743997","268810000021199744000","268810000021199745010","268810000021199746020",
                    "268810000021199747030","268810000021199748048","268810000021199749058","268810000021199750063",
                    "268810000021199751073","268810000021199752085","268810000021199860164","268810000021199861174",
                    "268810000021199872280","268810000021199874305","268810000021199875315","268810000021199876325",
                    "268810000021199877335","268810000021199878347","268810000021199882384","268810000021199883394",
                    "268810000021199884407","268810000021199885417","268810000021199888445","268810000021199889455",
                    "268810000021199890464","268810000021199891474","268810000021199892488","268810000021199894507",
                    "268810000021199895517","268810000021199896527","268810000021199906622","268810000021199913693",
                    "268810000021199914702","268810000021199921773","268810000021199929858","268810000021199930863",
                    "268810000021199931873","268810000021199932885","268810000021199934900","268810000021199943999",
                    "268810000021199944004","268810000021199945014","268810000021199947034","268810000021199948046",
                    "268810000021199951073","268810000021199952081","268810000021199953091","268810000021199954104",
                    "268810000021199955114","268810000021199956124","268810000021199957134","268810000021199958146",
                    "268810000021199959156","268810000021199960165","268810000021199981373","268810000021200004606",
                    "268810000021200012698","268810000021200014717","268810000021200034911","268810000021200035929",
                    "268810000021200036931","268810000021200038953","268810000021200040974","268810000021200041980",
                    "268810000021200061164","268810000021200092429","268810000021200093439","268810000021200094449",
                    "268810000021200095459","268810000021200096469","268810000021200097479","268810000021200105550",
                    "268810000021200106560","268810000021200107570","268810000021200192428","268811100000387220716",
                    "268811100000387220717","gagagagaga","gagagagagaa"};
            PresureTestTools<String> testTools = new PresureTestTools<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    //User u = userDao.selectUserByID(id[count++]);
                    //RedisUtils.put(String.valueOf(u.getRole_id())+ UUID.randomUUID().toString(), u);
                    //RedisUtils.del(String.valueOf(u.hashCode()));
                    RedisUtils.delAll();
                    return null;
                }
            }, 1, 500);
            testTools.run();
    }

    public void test() {

        StringBuilder stringBuilder = new StringBuilder();
        List<String> id = new LinkedList<String>();

        List<User> alluser = userDao.selectAllUser();
        log.info("拿到的用户个数"+alluser.size());

        log.info("初始化用户ID成功");
        int count= 0;
        for(User u : alluser){
            stringBuilder.append("\""+u.getRole_id()+"\",");
            if(count%4==0){
                stringBuilder.append("\n");
            }
            count++;
        }
        log.info(stringBuilder.toString());
    }


    public void adduser(){
        for(int i=0;i<=999;i++){
            User u = new User();
            u.setRole_id(UUID.randomUUID().toString());
            u.setSource("UUID");
            userDao.addUser(u);
        }
    }
}
