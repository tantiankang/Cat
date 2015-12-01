
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.Scene;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.vecmath.*;

import java.io.*;

import com.sun.j3d.utils.behaviors.vp.*;

import java.net.URL;
import java.net.MalformedURLException;

public class ObjLoad extends Applet  {

	JButton label = new JButton("Press click to start game");
    private boolean spin = false;
    private boolean noTriangulate = false;
    private boolean noStripify = false;
    private double creaseAngle = 60.0;
    private URL filename = null;
    private SimpleUniverse u;
    private BoundingSphere bounds;

    public BranchGroup createSceneGraph() {
	// Create the root of the branch graph
	BranchGroup objRoot = new BranchGroup();

        // Create a Transformgroup to scale all objects so they
        // appear in the scene.
        TransformGroup objScale = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.7);
        objScale.setTransform(t3d);
        objRoot.addChild(objScale);

	// Create the transform group node and initialize it to the
	// identity.  Enable the TRANSFORM_WRITE capability so that
	// our behavior code can modify it at runtime.  Add it to the
	// root of the subgraph.
	TransformGroup objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	objScale.addChild(objTrans);

	int flags = ObjectFile.RESIZE;
	if (!noTriangulate) flags |= ObjectFile.TRIANGULATE;
	if (!noStripify) flags |= ObjectFile.STRIPIFY;
	ObjectFile f = new ObjectFile(flags, 
	  (float)(creaseAngle * Math.PI / 180.0));
	Scene s = null;
	try {
	  s = f.load(filename);
	}
	catch (Exception e) {
	  System.err.println(e);
	  System.exit(1);
	}

	  
	objTrans.addChild(s.getSceneGroup());

	bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        if (true) {
	  Transform3D yAxis = new Transform3D();
	  Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE,
					  0, 0,
					  4000, 0, 0,
					  0, 0, 0);

	  RotationInterpolator rotator =
	      new RotationInterpolator(rotationAlpha, objTrans, yAxis,
				       0.0f, (float) Math.PI*2.0f);
	  rotator.setSchedulingBounds(bounds);
	  objTrans.addChild(rotator);
	} 

        // Set up the background
        Color3f bgColor = new Color3f(0f, 0f, 0f);
        Background bgNode = new Background(bgColor);
        bgNode.setApplicationBounds(bounds);
       objRoot.addChild(bgNode);

	return objRoot;
    }

    private void usage()
    {
      System.exit(0);
    } // End of usage



    public void init() {

    	label.setOpaque(true);
    	label.setBackground(Color.BLACK);
    	label.setForeground(Color.YELLOW);
    	label.setFont(new Font("", Font.PLAIN, 30));
    	label.setSize(400, 50);
    	label.setLocation(300, 600);
    	label.setBorderPainted(false);
    	this.add(label);
    	this.setFocusable(true);
    	label.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CAT();
			}

    	});
			
	if (filename == null) {
            // Applet
            try {
                URL path = getCodeBase();
                filename = new URL("file:///C://Users//Asus//Desktop//Java Work//3D//J3DGettingStart//Cat.obj");
            }
            catch (MalformedURLException e) {
	                System.err.println(e);
	                System.exit(1);
            }
	}

	setLayout(new BorderLayout());
    GraphicsConfiguration config =SimpleUniverse.getPreferredConfiguration();

    Canvas3D c = new Canvas3D(config);
	add("Center", c);

	// Create a simple scene and attach it to the virtual universe
	BranchGroup scene = createSceneGraph();
	u = new SimpleUniverse(c);
	
	addLights(scene);
	addObjects(scene);
	
	// add mouse behaviors to the ViewingPlatform
	ViewingPlatform viewingPlatform = u.getViewingPlatform();

	PlatformGeometry pg = new PlatformGeometry();

	// Set up the ambient light
	Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
	AmbientLight ambientLightNode = new AmbientLight(ambientColor);
	ambientLightNode.setInfluencingBounds(bounds);
	pg.addChild(ambientLightNode);

	// Set up the directional lights
	Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
	Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
	Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
	Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

	DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
	light1.setInfluencingBounds(bounds);
	pg.addChild(light1);

	DirectionalLight light2 = new DirectionalLight(light2Color, light2Direction);
	light2.setInfluencingBounds(bounds);
	pg.addChild(light2);

	viewingPlatform.setPlatformGeometry( pg );
      
	// This will move the ViewPlatform back a bit so the
	// objects in the scene can be viewed.
	viewingPlatform.setNominalViewingTransform();

	if (!spin) {
            OrbitBehavior orbit = new OrbitBehavior(c,
						    OrbitBehavior.REVERSE_ALL);
            BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
            orbit.setSchedulingBounds(bounds);
            viewingPlatform.setViewPlatformBehavior(orbit);	    
	}

	u.addBranchGraph(scene);
	
	
    }

    void addObjects(BranchGroup scene) {
		// TODO Auto-generated method stub
    	Font3D f3d = new Font3D(new Font("", Font.PLAIN, 2),
				new FontExtrusion());
		Text3D text = new Text3D(f3d, new String("Cats vs Cats"), new Point3f(-3.5f,
				1f, -4.5f));
		 
		text.setString("Cats vs Cats");
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f blue = new Color3f(.2f, 0.2f, 0.6f);
		Appearance a = new Appearance();
		Material m = new Material(blue, blue, blue, white, 80.0f);
		m.setLightingEnable(true);
		a.setMaterial(m);

		Shape3D sh = new Shape3D();
		sh.setGeometry(text);
		sh.setAppearance(a);
		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
		Transform3D tDown = new Transform3D();
		Transform3D rot = new Transform3D();
		Vector3f v3f = new Vector3f(-2.0f, -1.35f, -6.5f);
		t3d.setTranslation(v3f);
		rot.rotX(Math.PI / 5);
		t3d.mul(rot);
		v3f = new Vector3f(0, -1.4f, 0f);
		tDown.setTranslation(v3f);
		t3d.mul(tDown);
		tg.setTransform(t3d);
		tg.addChild(sh);
		scene.addChild(tg);
	}

	 void addLights(BranchGroup scene) {
		// TODO Auto-generated method stub
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				1000.0);

		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color,
				light1Direction);
		light1.setInfluencingBounds(bounds);
		scene.addChild(light1);

		// Set up the ambient light
		Color3f ambientColor = new Color3f(.1f, .1f, .1f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		scene.addChild(ambientLightNode);
	}

	// Caled if running as a program
    public ObjLoad(String[] args) {
      if (args.length != 0) {
	for (int i = 0 ; i < args.length ; i++) {
	  if (args[i].startsWith("-")) {
	    if (args[i].equals("-s")) {
	      spin = true;
	    } else if (args[i].equals("-n")) {
	      noTriangulate = true;
	    } else if (args[i].equals("-t")) {
	      noStripify = true;
	    } else if (args[i].equals("-c")) {
	      if (i < args.length - 1) {
		creaseAngle = (new Double(args[++i])).doubleValue();
	      } else usage();
	    } else {
	      usage();
	    }
	  } else {
	    try {
	      if ((args[i].indexOf("file:") == 0) ||
		  (args[i].indexOf("http") == 0)) {
		  filename = new URL(args[i]);
	      }
	      else if (args[i].charAt(0) != '/') {
		  filename = new URL("file:./" + args[i]);
	      }
	      else {
		  filename = new URL("file:" + args[i]);
	      }
            }
	    catch (MalformedURLException e) {
	      System.err.println(e);
	      System.exit(1);
	    }
	  }
	}
      }
    }

    // Running as an applet
    public ObjLoad() {
    }

    public void destroy() {
	u.cleanup();
    }


    //
    // The following allows ObjLoad to be run as an application
    // as well as an applet
    //
    public static void main(String[] args) {
      new MainFrame(new ObjLoad(args), 900, 700);
    }


}
