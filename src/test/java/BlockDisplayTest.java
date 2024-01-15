import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import shwendel.fishing.Fishing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class BlockDisplayTest {

    // Used to test JSON because I'm a JSON noob. Who is Jason? Did I mention JSON?

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        File file = new File(new File(".").getAbsolutePath() + "\\src\\main\\resources\\model\\helm.json");

        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file.getPath()));

        JSONArray passenger = (JSONArray) jsonObject.get("Passengers");

        for(int i = 0; i < passenger.size(); i++) {

            JSONObject object = (JSONObject) passenger.get(i);

            Transformation transformation = new Transformation(
                    new Vector3f(0.75f, 0, 0),
                    new AxisAngle4f(-0.1875f, 0, 0.75f, 0),
                    new Vector3f(2.0625f, 0, 0),
                    new AxisAngle4f(0, 0, 0, 0)
            );


        }


    }

    public final void set(Matrix4f m1) {

        double w = Math.sqrt(1.0 + m1.m00() + m1.m11() + m1.m22()) / 2.0;

        double w4 = (4.0 * w);

        double x = (m1.m21() - m1.m12()) / w4;
        double y = (m1.m02() - m1.m20()) / w4;
        double z = (m1.m10() - m1.m01()) / w4;



    }

}
