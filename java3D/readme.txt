Source available upon request.  Updated 2020-2-15 to run on Java3D 64 bit version, compiled in Eclipse on Win10.

A partial code snippet is shown below from the eulerTop class (initialization function only)

    public void init() {

        System.out.println("Mode:"+mode+" Delay(ms): To be determined by user");
        if(started==0){
        // TODO start asynchronous download of heavy resources
        //pane=getContentPane();
        borderLayout=new BorderLayout();
        int Hgap=borderLayout.getHgap(),Vgap=borderLayout.getVgap();
        //borderLayout.
        //System.out.println("HGap:"+Hgap+"VGap:"+Vgap);
        
        setLayout(borderLayout);

        c=new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
        add("Center",c);
        
        //BranchGroup scene;//=CreateScene();
        //scene.compile();
        simpleU=new SimpleUniverse(c);
        
        viewPlatform=simpleU.getViewingPlatform();
        viewPlatform.setNominalViewingTransform();
        mouseOrbit=new OrbitBehavior(c, OrbitBehavior.REVERSE_ALL |
				  OrbitBehavior.STOP_ZOOM);
        mouseOrbit.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));
        //DO NOT add mouse Orbit to viewPlatform, use mouse behaviors that add onto Group: objSceneOrientation
        //viewPlatform.setViewPlatformBehavior(mouseOrbit);
        
        
        //transformGroup=new TransformGroup();

        objRoot=new BranchGroup();
        objObject3D=new TransformGroup();
        objRotate=new TransformGroup();
        objRotateAxes=new TransformGroup();
        objSceneTop=new TransformGroup();
        objFixedAxes=new TransformGroup();
        objRoom=new TransformGroup();
        objTable=new TransformGroup();
        objScene=new TransformGroup();
        objSceneShift=new TransformGroup();
        objSceneLocation=new TransformGroup();
        objSceneOrientationMouseRotate=new TransformGroup();
        objSceneOrientationMouseTranslate=new TransformGroup();
        objSceneOrientationMouseZoom=new TransformGroup();
        boxDoorRotator=new TransformGroup();

        
        objSceneRoot=new TransformGroup();
        transformGroup=new TransformGroup();
        objRoot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objRoot.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);        

        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objRotateAxes.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objRotateAxes.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objObject3D.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objObject3D.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objSceneTop.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objSceneTop.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objFixedAxes.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        objFixedAxes.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objRoom.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTable.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objScene.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneRoot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneShift.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneLocation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneOrientationMouseRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneOrientationMouseRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objSceneOrientationMouseTranslate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneOrientationMouseTranslate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objSceneOrientationMouseZoom.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objSceneOrientationMouseZoom.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        boxDoorRotator.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        boxDoorRotator.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        
        BoundingSphere mouseBounds = new BoundingSphere(new Point3d(), 1000.0);
        MouseRotate myMouseRotate = new MouseRotate();//MouseBehavior.INVERT_INPUT);
        myMouseRotate.setTransformGroup(objSceneOrientationMouseRotate);
        myMouseRotate.setSchedulingBounds(mouseBounds);
        objRoot.addChild(myMouseRotate);
        MouseTranslate myMouseTranslate = new MouseTranslate(MouseBehavior.INVERT_INPUT);
        myMouseTranslate.setTransformGroup(objSceneOrientationMouseTranslate);
        myMouseTranslate.setSchedulingBounds(mouseBounds);
        objRoot.addChild(myMouseTranslate);
        MouseZoom myMouseZoom = new MouseZoom(MouseBehavior.INVERT_INPUT);
        myMouseZoom.setTransformGroup(objSceneOrientationMouseZoom);
        myMouseZoom.setSchedulingBounds(mouseBounds);
        objRoot.addChild(myMouseZoom);        
        
        
        //Initialize appearance instance, with capability to Write Textures        
	app = new Appearance();
        app.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        app.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        app.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
	appTable = new Appearance();
        appTable.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        appTable.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        appTable.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
	appWalls = new Appearance();
        appWalls.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        appWalls.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        appWalls.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
	appFloor = new Appearance();
        appFloor.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        appFloor.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        appFloor.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
	appDoor1 = new Appearance();
        appDoor1.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        appDoor1.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        appDoor1.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
	appDoor2 = new Appearance();
        appDoor2.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        appDoor2.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
        appDoor2.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
        setTex("table",useHighres?"highres":"lowres");
        setTex("walls",useHighres?"highres":"lowres");
        setTex("floor",useHighres?"highres":"lowres");        
        setTex("door1",useHighres?"highres":"lowres");
        setTex("door2",useHighres?"highres":"lowres");      
        appearancesSet=true;
        // Create a bounds for the background and behaviors
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        
	// Set up the background
        bgColor=new Color(0.05f,0.05f,0.15f);
	bgColorf = new Color3f(bgColor);
	Background bg = new Background(bgColorf);
        //ImageComponent2D image=new ImageComponent2D();
        //bg.setImage(image);
	bg.setApplicationBounds(bounds);
	objRoot.addChild(bg);        
	// Set up the global lights

        Color3f alColor = new Color3f(0.19f, 0.19f, 0.19f);

	Color3f lColor1 = new Color3f(0.2f, 0.2f, 0.83f);        
        Color3f lColor2 = new Color3f(0.0f,0.0f,0.85f);
        Color3f lColor3 = new Color3f(0.0f,0.20f,0.8950f);
	Vector3f lDir1  = new Vector3f(1.0f/4f,-.80f,-1.20f/4f);//-1.0f, -1.0f, -1.0f);
        Vector3f lDir2  = new Vector3f(-.50f,.0f,1.0f);
        Vector3f lDir3  = new Vector3f(.5f,2.0f,.50f);
	AmbientLight aLgt = new AmbientLight(alColor);
	aLgt.setInfluencingBounds(bounds);
	DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
        DirectionalLight lgt2 = new DirectionalLight(lColor2,lDir2);
        DirectionalLight lgt3 = new DirectionalLight(lColor3,lDir3);
        
        lgt1.setInfluencingBounds(bounds);
        lgt2.setInfluencingBounds(bounds);
        lgt3.setInfluencingBounds(bounds);
        objRoot.addChild(aLgt);//Ambient Light
	objRoot.addChild(lgt1);//Light1 (lgt1)
        objRoot.addChild(lgt2);//Light2 (lgt2)
        objRoot.addChild(lgt3);
        }
        
        
        if(started==1){
        Appearance axesApp=new Appearance();
        Color3f axesColor = new Color3f(.0f, 1.00f, 0.3500f);
	axesApp.setMaterial(new Material(axesColor, black, axesColor,
					     white, 80.0f));
///////////////////////////////////////////////////////////////////////
        Appearance axesAppBody=new Appearance();
        Color3f axesColorBody = new Color3f(.5f, .7f, 0.0500f);
	axesAppBody.setMaterial(new Material(axesColorBody, black, axesColorBody,
					     white, 80.0f));
////////////////////////////////////////////////////////////////////////        
        Color3f objColor=new Color3f(.440f, .500f, .98f);
                
	Material mm = new Material(new Color3f(0.2f,0.2f,0.2f), black, objColor,
					     white, 90.0f);
	mm.setLightingEnable(true);
        //ColoringAttributes ca=new ColoringAttributes();
        //ca.setColor(new Color3f(1.0f,0f,0f));
	app.setMaterial(mm);
        //app.setColoringAttributes(ca);
        
        //Cone(radius(f),Height(f),app(Appearance));
        if(ratioHeightToRadius>=0.05&&ratioHeightToRadius<=15.0){
            radiusObject3D=(float)Math.pow((0.75/ratioHeightToRadius),0.5);
            heightObject3D=(float)ratioHeightToRadius*radiusObject3D;
        }
        else {
            radiusObject3D=0.5f;
            heightObject3D=1.4f;
        }
        //Object smoothnessStatus=;
        
        smoothnessObject3D=Integer.parseInt(smoothnessBox.getSelectedItem().toString());
        smoothnessLabel.setEnabled(false);
        smoothnessBox.setEnabled(false);
        currentTransform=new Transform3D();
        currentTransform.setScale(0.5);
        switch(object3DInt){
            case 0:
                cone = new Cone(radiusObject3D,heightObject3D,Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,smoothnessObject3D,smoothnessObject3D,app);   //-sl/2.0f,-1.f,-1.f
                objObject3D.addChild(cone);
                currentTransform.setTranslation(new Vector3d(0,heightObject3D/4,0));
                currentTransform.setRotation(new AxisAngle4d(new Vector3d(0,0,1),Math.PI));

                break;
            case 1:
                Cylinder topCylinder=new Cylinder(radiusObject3D,heightObject3D,Cylinder.GENERATE_TEXTURE_COORDS | 
			     Cylinder.GENERATE_NORMALS,smoothnessObject3D,smoothnessObject3D,app);                
                objObject3D.addChild(topCylinder);
                currentTransform.setTranslation(new Vector3d(0,heightObject3D/4,0));
                break;
            default:
                System.out.println("Unknown Object Selected!");
        }

        transformGroup=new TransformGroup(currentTransform);
        //simpleU.addBranchGraph(scene);      
        transformGroup.addChild(objObject3D);

        //double alpha=Math.atan(radiusObject3D/heightCone);
        //double theta2=2*alpha;
        //Cone cone2=new Cone((float)(radiusCone*Math.sin(alpha)*Math.sin(theta2-alpha)),
        //                    (float)(radiusCone*Math.sin(alpha)*Math.cos(theta2-alpha)),app );
        currentTransform=new Transform3D();
//////////////////////////////////////////////////////////
       //currentTransform);

        ///////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////
        Matrix3d eulerPhi=new Matrix3d();Matrix3d eulerTheta=new Matrix3d();
        Matrix3d eulerPsi=new Matrix3d();
        double psi=Math.PI*-60/180,phi=Math.PI*60/180,theta=Math.PI*180/180;
        eulerPhi.m00=Math.cos(phi);     eulerPhi.m01=Math.sin(phi); 	eulerPhi.m02=0;
        eulerPhi.m10=-Math.sin(phi);    eulerPhi.m11=Math.cos(phi);     eulerPhi.m12=0;
        eulerPhi.m20=0;                 eulerPhi.m21=0;                 eulerPhi.m22=1;

        eulerTheta.m00=1;               eulerTheta.m01=0;               eulerTheta.m02=0;
        eulerTheta.m10=0;               eulerTheta.m11=Math.cos(theta); eulerTheta.m12=Math.sin(theta);
        eulerTheta.m20=0;               eulerTheta.m21=-Math.sin(theta);eulerTheta.m22=Math.cos(theta);

        eulerPsi.m00=Math.cos(psi);     eulerPsi.m01=Math.sin(psi); 	eulerPsi.m02=0;
        eulerPsi.m10=-Math.sin(phi);    eulerPsi.m11=Math.cos(psi);     eulerPsi.m12=0;
        eulerPsi.m20=0;                 eulerPsi.m21=0;                 eulerPsi.m22=1;
        ///////////////////////////////////////////////////////////////////////////////        
        //currentTransform.setTranslation(new Vector3d(0,(float)(radiusCone*Math.sin(alpha)*Math.cos(theta2-alpha))/4,0));
        
        ///////////////////////////////////////////////////////////////////////////////
        /////////////////End CurrentTransform//////////////////////////////////
        TransformGroup transformGroupText=new TransformGroup();
        TransformGroup transformGroupText2=new TransformGroup();
        TransformGroup transformXAxisText=new TransformGroup();
        TransformGroup transformYAxisText=new TransformGroup();
        TransformGroup transformZAxisText=new TransformGroup();
        TransformGroup transformXAxisMirrorText=new TransformGroup();
        TransformGroup transformYAxisMirrorText=new TransformGroup();
        TransformGroup transformZAxisMirrorText=new TransformGroup();

        TransformGroup transformXAxis=new TransformGroup();
        TransformGroup transformYAxis=new TransformGroup();
        TransformGroup transformZAxis=new TransformGroup();
        TransformGroup transformGroupAxes=new TransformGroup();
////////////////////////////////////////////////////////////////////////////////////////
        TransformGroup transformGroupTextBody=new TransformGroup();
        TransformGroup transformXAxisTextBody=new TransformGroup();
        TransformGroup transformYAxisTextBody=new TransformGroup();
        TransformGroup transformZAxisTextBody=new TransformGroup();
        TransformGroup transformXAxisMirrorTextBody=new TransformGroup();
        TransformGroup transformYAxisMirrorTextBody=new TransformGroup();
        TransformGroup transformZAxisMirrorTextBody=new TransformGroup();

        TransformGroup transformXAxisBody=new TransformGroup();
        TransformGroup transformYAxisBody=new TransformGroup();
        TransformGroup transformZAxisBody=new TransformGroup();
        TransformGroup transformGroupAxesBody=new TransformGroup();
        titleForceFreeTranslation=new TransformGroup();
        titleGravityTranslation=new TransformGroup();
        titleForceFreeTranslation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
        titleGravityTranslation.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);        
/////////////////////////////////////////////////////////////////////////////////////////        
        
        currentTransform.setScale(0.5);
        Transform3D transform3D=new Transform3D();
        Transform3D textTransform=new Transform3D();
        
        Text2D text2D = new Text2D("Force Free Top Using Euler Angles",
            new Color3f(0.79f, 1.0f, .80f),"Helvetica", 36, Font.BOLD);
        int length=text2D.getString().length();
        currentTransform.setTranslation(new Vector3d(-(float)length/(2*10*3),0.77,0.05));
        transformGroupText.addChild(text2D);
        transformGroupText.setTransform(currentTransform);
        titleForceFreeTranslation.addChild(transformGroupText);
        
        Text2D text2D2 = new Text2D("Euler Top with Gravity",
            new Color3f(0.79f, 1.0f, .80f),"Helvetica", 36, Font.BOLD);
        length=text2D2.getString().length();
        currentTransform.setTranslation(new Vector3d(-(float)length/(2*10*3),0.77,0.05));
        transformGroupText2.addChild(text2D2);
        transformGroupText2.setTransform(currentTransform);
        titleGravityTranslation.addChild(transformGroupText2);
        setTitle();
        double mirrorShift=0.086;//Mirror Shift equals length of X*' or X*
        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////// CREATE FIXED AXES /////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////
        Cylinder xAxis=new Cylinder(0.004f,2.0f,axesApp),yAxis=new Cylinder(0.004f,2.0f,axesApp),zAxis=new Cylinder(0.004f,2.0f,axesApp);
        

        Text2D textX = new Text2D("X2' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        Text2D textXMirror=new Text2D("X2' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        transformXAxisText.addChild(textX);
        transformXAxisMirrorText.addChild(textXMirror);
        
        Text2D textY = new Text2D("X3' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        Text2D textYMirror=new Text2D("X3' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        transformYAxisText.addChild(textY);
        transformYAxisMirrorText.addChild(textYMirror);
        
        Text2D textZ = new Text2D("X1' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        Text2D textZMirror=new Text2D("X1' ",
            axesColor,"Helvetica", 36, Font.BOLD);
        transformZAxisText.addChild(textZ);
        transformZAxisMirrorText.addChild(textZMirror);        
  
        transformXAxis.addChild(xAxis);
        transformYAxis.addChild(yAxis);
        transformZAxis.addChild(zAxis);
        transformGroupAxes.addChild(transformXAxis);
        transformGroupAxes.addChild(transformYAxis);
        transformGroupAxes.addChild(transformZAxis);
        transformGroupAxes.addChild(transformXAxisText);
        transformGroupAxes.addChild(transformYAxisText);
        transformGroupAxes.addChild(transformZAxisText);
        transformGroupAxes.addChild(transformXAxisMirrorText);
        transformGroupAxes.addChild(transformYAxisMirrorText);
        transformGroupAxes.addChild(transformZAxisMirrorText);
        textTransform.setScale(0.5);

        
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0d,0d,0d),0.0));
        textTransform.setTranslation(new Vector3d(0.8,0,-0.05));
        transformXAxisText.setTransform(textTransform);
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI));
        textTransform.setTranslation(new Vector3d(0.8+mirrorShift,0,-0.05));
        transformXAxisMirrorText.setTransform(textTransform);

        textTransform.setRotation(new AxisAngle4d(new Vector3d(0d,0d,0d),0.0));
        textTransform.setTranslation(new Vector3d(0,0.7,0.05));
        transformYAxisText.setTransform(textTransform);
        textTransform.setTranslation(new Vector3d(0+mirrorShift,0.7,0.05));
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI));
        transformYAxisMirrorText.setTransform(textTransform);

        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI/2));
        textTransform.setTranslation(new Vector3d(0.05,0,0.85));
        transformZAxisText.setTransform(textTransform);
        textTransform.setTranslation(new Vector3d(0.05,0,0.85+mirrorShift));
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),-Math.PI/2));
        transformZAxisMirrorText.setTransform(textTransform);
        
        
        transform3D.setTranslation(new Vector3d(0,0,0));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(0,0,1),Math.PI/2));
        transformXAxis.setTransform(transform3D);
        transform3D.setRotation(new AxisAngle4d(new Vector3d(1,0,0),Math.PI/2));
        transformZAxis.setTransform(transform3D);

        objFixedAxes.addChild(titleForceFreeTranslation);
        objFixedAxes.addChild(titleGravityTranslation);
        objFixedAxes.addChild(transformGroupAxes);
        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////end  CREATE FIXED AXES /////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////// CREATE BODY AXES /////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////
        Cylinder xAxisBody=new Cylinder(0.004f,2.0f,axesAppBody),yAxisBody=new Cylinder(0.004f,2.0f,axesAppBody),zAxisBody=new Cylinder(0.004f,2.0f,axesAppBody);
        

        Text2D textXBody = new Text2D("X2 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        Text2D textXMirrorBody=new Text2D("X2 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        transformXAxisTextBody.addChild(textXBody);
        transformXAxisMirrorTextBody.addChild(textXMirrorBody);
        
        Text2D textYBody = new Text2D("X3 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        Text2D textYMirrorBody=new Text2D("X3 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        transformYAxisTextBody.addChild(textYBody);
        transformYAxisMirrorTextBody.addChild(textYMirrorBody);
        
        Text2D textZBody = new Text2D("X1 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        Text2D textZMirrorBody=new Text2D("X1 ",
            axesColorBody,"Helvetica", 36, Font.BOLD);
        transformZAxisTextBody.addChild(textZBody);
        transformZAxisMirrorTextBody.addChild(textZMirrorBody);        
  
        transformXAxisBody.addChild(xAxisBody);
        transformYAxisBody.addChild(yAxisBody);
        transformZAxisBody.addChild(zAxisBody);
        transformGroupAxesBody.addChild(transformXAxisBody);
        transformGroupAxesBody.addChild(transformYAxisBody);
        transformGroupAxesBody.addChild(transformZAxisBody);
        transformGroupAxesBody.addChild(transformXAxisTextBody);
        transformGroupAxesBody.addChild(transformYAxisTextBody);
        transformGroupAxesBody.addChild(transformZAxisTextBody);
        transformGroupAxesBody.addChild(transformXAxisMirrorTextBody);
        transformGroupAxesBody.addChild(transformYAxisMirrorTextBody);
        transformGroupAxesBody.addChild(transformZAxisMirrorTextBody);
        textTransform=new Transform3D();
        textTransform.setScale(0.5);

        
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0d,0d,0d),0.0));
        textTransform.setTranslation(new Vector3d(0.8,0,-0.05));
        transformXAxisTextBody.setTransform(textTransform);
        textTransform.setTranslation(new Vector3d(0.8+mirrorShift,0,-0.05));
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI));
        transformXAxisMirrorTextBody.setTransform(textTransform);

        textTransform.setRotation(new AxisAngle4d(new Vector3d(0d,0d,0d),0.0));
        textTransform.setTranslation(new Vector3d(0,0.7,0.05));
        transformYAxisTextBody.setTransform(textTransform);
        textTransform.setTranslation(new Vector3d(mirrorShift,0.7,0.05));
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI));
        transformYAxisMirrorTextBody.setTransform(textTransform);

        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),Math.PI/2));
        textTransform.setTranslation(new Vector3d(0.05,0,0.85));
        transformZAxisTextBody.setTransform(textTransform);
        textTransform.setRotation(new AxisAngle4d(new Vector3d(0,-1,0),-Math.PI/2));
        textTransform.setTranslation(new Vector3d(0.05,0,0.85+mirrorShift));
        transformZAxisMirrorTextBody.setTransform(textTransform);
        
        transform3D.setTranslation(new Vector3d(0,0,0));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(0,0,1),Math.PI/2));
        transformXAxisBody.setTransform(transform3D);
        transform3D.setRotation(new AxisAngle4d(new Vector3d(1,0,0),Math.PI/2));
        transformZAxisBody.setTransform(transform3D);


        ////////////////////////////////////////////////////////////////////////////////
        ////////////////////////end  CREATE BODY AXES /////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

        objSceneTop.addChild(objFixedAxes);
	//Shape3D sh = new Shape3D();
	//sh.setGeometry(txt);
	//sh.setAppearance(app);        
       	//objTrans.addChild(sh);        
        ///////////////////////////////// Attach Object3D
        
        //objRoot.addChild(cone2);
        //

        //MouseRotate myMouseRotate=new MouseRotate();
        //myMouseRotate.setTransformGroup(objMouseRotate);
        //myMouseRotate.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0));
        //objRoot.addChild(myMouseRotate);
        //Apply Basic Transform

        objRotate.addChild(transformGroup);
        objSceneTop.addChild(objRotate);
        
        currentTransform=new Transform3D();
        transformGroupAxes=new TransformGroup(currentTransform);
        transformGroupAxes.addChild(transformGroupTextBody);
        transformGroupAxes.addChild(transformGroupAxesBody);
        objRotateAxes.addChild(transformGroupAxes);
        objSceneTop.addChild(objRotateAxes); 
        objTable.addChild(objSceneTop);

        /////////////////////////////////////////////////////////////////
        //////////////// SET APPEARANCE OF TABLE ///////////////////////
        ////////////////////////////////////////////////////////////////
        
        objColor=new Color3f(.840f, .500f, .68f);
        if(!appearancesSet)
            setTex("table",useHighres?"highres":"lowres");
        
        /////////////////////////////////////////////////////////////////
        /////////////END SET APPEARANCE OF TABLE ///////////////////////
        ////////////////////////////////////////////////////////////////
        
        float heightTable=0.14f,widthTable=4.1f,depthTable=3.3f,legsHeight=3.1f,legsDiameter=0.4f;
        com.sun.j3d.utils.geometry.Box BoxObj = new com.sun.j3d.utils.geometry.Box(widthTable, heightTable, depthTable, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appTable, 2);    
        int smoothnessLegs=10;
        TransformGroup tableTop=new TransformGroup();
        TransformGroup leg1Transform=new TransformGroup();
        TransformGroup leg2Transform=new TransformGroup();
        TransformGroup leg3Transform=new TransformGroup();
        TransformGroup leg4Transform=new TransformGroup();
        
        transform3D=new Transform3D();
        transform3D.setTranslation(new Vector3d(0,-heightTable,0));
        tableTop.setTransform(transform3D);
        tableTop.addChild(BoxObj);
        objTable.addChild(tableTop);
        ////////////////////////////////////////////CREATE LEGS OF TABLE //////////////////////////
        Cylinder leg1=new Cylinder(legsDiameter/2f,legsHeight,Cylinder.GENERATE_TEXTURE_COORDS | 
			     Cylinder.GENERATE_NORMALS,smoothnessLegs,smoothnessLegs,appTable);
        Cylinder leg2=new Cylinder(legsDiameter/2f,legsHeight,Cylinder.GENERATE_TEXTURE_COORDS | 
			     Cylinder.GENERATE_NORMALS,smoothnessLegs,smoothnessLegs,appTable);
        Cylinder leg3=new Cylinder(legsDiameter/2f,legsHeight,Cylinder.GENERATE_TEXTURE_COORDS | 
			     Cylinder.GENERATE_NORMALS,smoothnessLegs,smoothnessLegs,appTable);
        Cylinder leg4=new Cylinder(legsDiameter/2f,legsHeight,Cylinder.GENERATE_TEXTURE_COORDS | 
			     Cylinder.GENERATE_NORMALS,smoothnessLegs,smoothnessLegs,appTable);        

        transform3D=new Transform3D();        
        leg1Transform.addChild(leg1);
        transform3D.setTranslation(new Vector3d(-.8*widthTable,-(legsHeight/2+heightTable),-.8*depthTable));
        leg1Transform.setTransform(transform3D);
        leg2Transform.addChild(leg2);
        transform3D.setTranslation(new Vector3d(.8*widthTable,-(legsHeight/2+heightTable),-.8*depthTable));
        leg2Transform.setTransform(transform3D);
        leg3Transform.addChild(leg3);
        transform3D.setTranslation(new Vector3d(.8*widthTable,-(legsHeight/2+heightTable),.8*depthTable));
        leg3Transform.setTransform(transform3D);
        leg4Transform.addChild(leg4);
        transform3D.setTranslation(new Vector3d(-.8*widthTable,-(legsHeight/2+heightTable),.8*depthTable));
        leg4Transform.setTransform(transform3D);

        
        objTable.addChild(leg1Transform);
        objTable.addChild(leg2Transform);
        objTable.addChild(leg3Transform);
        objTable.addChild(leg4Transform);
        transform3D.setTranslation(new Vector3d(0,0,0));
        //transform3D.setRotation(new AxisAngle4d(new Vector3d(0,0,1),Math.PI/6));
        objTable.setTransform(transform3D);
        ///////////////////////////////
        //SET APPEARANCE OF WALLS /////
        ///////////////////////////////
        
        objColor=new Color3f(.840f, .500f, .68f);
        if(!appearancesSet)
            setTex("walls",useHighres?"highres":"lowres");
        ////////////////////////////////////
        //////// SET APP OF FLOOR     //////
        ////////////////////////////////////
        
        objColor=new Color3f(.840f, .500f, .68f);
        if(!appearancesSet)
            setTex("floor",useHighres?"highres":"lowres");
        ///////////////////////////////////////
        //// END SET APPEARANCE OF FLOOR //////
        
        float widthFloor=widthTable*2.5f,depthFloor=widthTable*2f,heightFloor=0.2f;

        
        com.sun.j3d.utils.geometry.Box boxFloor = new com.sun.j3d.utils.geometry.Box(widthFloor, heightFloor, depthFloor, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appFloor, 2);    

        TransformGroup boxFloorTransform=new TransformGroup();
        transform3D=new Transform3D();
        transform3D.setTranslation(new Vector3d(0,-(legsHeight+heightTable+heightFloor),0));
        boxFloorTransform.setTransform(transform3D);
        boxFloorTransform.addChild(boxFloor);
        objRoom.addChild(boxFloorTransform);

        float toCeiling=3.75f*1.5f,widthWall1=depthFloor,depthWall1=0.07f,heightWall1=toCeiling;

        
        com.sun.j3d.utils.geometry.Box boxWall1 = new com.sun.j3d.utils.geometry.Box(widthWall1, heightWall1, depthWall1, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appWalls, 2);    

        TransformGroup boxWall1Transform=new TransformGroup();
        transform3D.setRotation(new AxisAngle4d(new Vector3d(0,1,0),Math.PI/2));
        transform3D.setTranslation(new Vector3d(-(widthFloor+depthWall1+.01),heightWall1-(heightFloor*2+heightTable+legsHeight),0));
        boxWall1Transform.setTransform(transform3D);
        boxWall1Transform.addChild(boxWall1);
        objRoom.addChild(boxWall1Transform);

        float widthWall2=widthFloor+depthWall1*2f,depthWall2=0.07f,heightWall2=toCeiling;        
        
        com.sun.j3d.utils.geometry.Box boxWall2 = new com.sun.j3d.utils.geometry.Box(widthWall2, heightWall2, depthWall2, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appWalls, 2);    

        TransformGroup boxWall2Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d(0,heightWall2-(heightFloor*2+heightTable+legsHeight),-(depthFloor+depthWall2+.01)));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxWall2Transform.setTransform(transform3D);
        boxWall2Transform.addChild(boxWall2);
        objRoom.addChild(boxWall2Transform);

        float scaleDoor=0.2f,widthWall01=(widthFloor+2f*depthWall1)*(1f-scaleDoor)/2f,depthWall01=0.07f,heightWall01=toCeiling;        
        
        com.sun.j3d.utils.geometry.Box boxWall01 = new com.sun.j3d.utils.geometry.Box(widthWall01, heightWall01, depthWall01, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appWalls, 2);    

        TransformGroup boxWall01Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d(-(widthFloor-widthWall01+2*depthWall1),heightWall2-(heightFloor*2+heightTable+legsHeight),(depthFloor+depthWall01+.01)));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxWall01Transform.setTransform(transform3D);
        boxWall01Transform.addChild(boxWall01);
        objRoom.addChild(boxWall01Transform);        

        float widthWall02=(widthFloor+2*depthWall1)*scaleDoor,depthWall02=0.07f,heightWall02=toCeiling*0.25f;        
        
        com.sun.j3d.utils.geometry.Box boxWall02 = new com.sun.j3d.utils.geometry.Box(widthWall02, heightWall02, depthWall02, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appWalls, 2);    

        TransformGroup boxWall02Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d(0,2*(heightWall01-heightWall02)+heightWall02-(heightFloor*2+heightTable+legsHeight),(depthFloor+depthWall02+.01)));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxWall02Transform.setTransform(transform3D);
        boxWall02Transform.addChild(boxWall02);
        objRoom.addChild(boxWall02Transform);        
        
        float widthWall03=widthWall01,depthWall03=depthWall01,heightWall03=heightWall01;        
        
        com.sun.j3d.utils.geometry.Box boxWall03 = new com.sun.j3d.utils.geometry.Box(widthWall03, heightWall03, depthWall03, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appWalls, 2);    

        TransformGroup boxWall03Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d((widthFloor-widthWall01+2*depthWall1),heightWall2-(heightFloor*2+heightTable+legsHeight),(depthFloor+depthWall01+.01)));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxWall03Transform.setTransform(transform3D);
        boxWall03Transform.addChild(boxWall03);
        objRoom.addChild(boxWall03Transform);    
        
        objColor=new Color3f(.840f, .500f, .68f);
        if(!appearancesSet)
            setTex("door1",useHighres?"highres":"lowres");
        if(!appearancesSet)
            setTex("door2",useHighres?"highres":"lowres");

        
        float widthDoor01=widthWall02,depthDoor01=depthWall02/2f,heightDoor01=(heightWall02*(1-0.25f)/0.25f);        
        
        com.sun.j3d.utils.geometry.Box boxDoor01 = new com.sun.j3d.utils.geometry.Box(widthDoor01, heightDoor01, depthDoor01, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appDoor1, 2);    

        TransformGroup boxDoor01Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d(widthDoor01,0,-depthDoor01));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxDoor01Transform.setTransform(transform3D);
        boxDoor01Transform.addChild(boxDoor01);
        com.sun.j3d.utils.geometry.Box boxDoor02 = new com.sun.j3d.utils.geometry.Box(widthDoor01, heightDoor01, depthDoor01, com.sun.j3d.utils.geometry.Box.GENERATE_NORMALS |
			    com.sun.j3d.utils.geometry.Box.GENERATE_TEXTURE_COORDS, appDoor2, 2);    

        TransformGroup boxDoor02Transform=new TransformGroup();
        transform3D.setTranslation(new Vector3d(widthDoor01,0,depthDoor01));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(0,1,0),0));
        boxDoor02Transform.setTransform(transform3D);
        boxDoor02Transform.addChild(boxDoor02);

        transform3D=new Transform3D();
        boxDoorRotator.setTransform(transform3D);
        boxDoorRotator.addChild(boxDoor01Transform);
        boxDoorRotator.addChild(boxDoor02Transform);        
        
        TransformGroup boxDoorTranslate=new TransformGroup();
        transform3D.setTranslation(new Vector3d(-widthDoor01,heightDoor01-(heightFloor*2+heightTable+legsHeight),(depthFloor+depthWall02+.01)));
        transform3D.setRotation(new AxisAngle4d(new Vector3d(),0));
        boxDoorTranslate.setTransform(transform3D);
        boxDoorTranslate.addChild(boxDoorRotator);
        
        objRoom.addChild(boxDoorTranslate);        
      
        
//////////////////////////////////////////////////////////////////////        
        objRoom.addChild(objTable);
        objSceneShift.addChild(objRoom);
        objSceneLocation.addChild(objSceneShift);
        objSceneOrientationMouseRotate.addChild(objSceneLocation);
        objSceneOrientationMouseTranslate.addChild(objSceneOrientationMouseRotate);
        objSceneOrientationMouseZoom.addChild(objSceneOrientationMouseTranslate);

        objScene.addChild(objSceneOrientationMouseZoom);
        objSceneRoot.addChild(objScene);
        transform3D=new Transform3D();
        transform3D.setScale(0.75);
        objSceneRoot.setTransform(transform3D);
        objRoot.addChild(objSceneRoot);
        //currentTransform.rotY(Math.PI/6);
        //currentTransform.rotZ(Math.PI/6);
        //objRotate.setTransform(currentTransform);       
        

        simpleU.addBranchGraph(CreateScene());

        }
        if(started==0){
            createMenu();
        }
    }
