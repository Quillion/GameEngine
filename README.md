<h1><b>Rules</b></h1>
<p>
<b><u><i>ALL</i></u></b> code must be commented. And all methods should have javadoc comments. (I am working on commenting my code now).
</p>

<h2><b>Directory explanation</b></h2>
<p>
<b>Engine:</b> All the general stuff for the code goes here. Further on more specialized code will go somewhere else.<br>
<b>Platformers:</b> Testing for platforming goes here, numbered testn where n is incrememntal, more complex each time.<br>
<b>Template:</b> Templates go here. Currently only contains basic example.<br>
<b>Examples:</b> Complete jars go here for just looking at.<br>
<b>Scripts:</b> Currently holds garbage from my old project.<br>
<b>Images:</b> Images and sprites go here.<br>
</p>

<h1><b>Objects so far:</b></h1>

<h2>Basic Shapes:</h2>

<p>
<b>QBox (Box):</b><br>
    A simple box,
    has x and y and width and hegith.
    Can call draw or drawBox to see what it looks like
<br>
<b>QBBox (Bounding Box):</b><br>
    Extension of QBox. A box with a bounding box
    Contains offsets which can be used for more realistic like collision detection.
</p>

<h2>Moving Shapes:</h2>

<p>
<b>QMBox (Moving Box):</b><br>
    Extension of QBox. A box which has vectors.
    Pretty useless by itself.
<br>
<b>QBMBox (Bounding Movement Box):</b><br>
    Extension of QBBox. A Bounding box which has vectors.
    Pretty useless by itself.
</p>

<h2>Extended Shapes:</h2>

<p>
<b>QMControls (Movement Controls):</b><br>
    Extension of QMBox. Introduces controls for the movement box.
<br>
<b>QBMControls (Bounding Movement Controls):</b><br>
    Extension of QBMBox. Introduces controls for the movement bounding box.
</p>

<h2>Basic Sprite:</h2>

<p>
<b>QPlatform (Platform):</b><br>
    Extension of QBox,
    contains both and image and color.
    If image is not loaded then draw function will draw whatever you set color to,
    default color is black.
<br>
<b>QBPlatform (Bounding Platform):</b><br>
    Extension of QBBox. Contains image only.
    Draw method will draw the image to canvas.
    Please make sure to load the image beforehand.
<br>
<b>QMCharacter (Moving Character):</b><br>
    This is a fuck up, and I got lazy to continue with this one.
    Will resume after I get to other ones.
</p>



<h2>Others:</h2>

<p>
<b>Main:</b><br>
    Contains brains of the game, don't touch it if you can help it.
<br>
<b>Game:</b><br>
    Contains functions which have to be populated for the game to work.
    Those functions are being called by Main. Much more simpler to read and understand.
<br>
<b>QConstants:</b><br>
    Has constant values that can be used.
</p>



<h2>Logic</h2>

<p>
<b>QCamera:</b><br>
    Camera controls. Using draw method here is better than using generic draw provided by the object.
<br>
<b>QEngine:</b><br>
    Collision detection. This will be used to check for collision between all the objects, be it platformer or crawler.
<br>
<b>QImageExtractor:</b><br>
    Give it an Image, and then you can use it to slice and dice the image and get the tinier pieces of it.
<br>
<b>QImageProcessor:</b><br>
    Used to glue the images together however you would like.
</p>

<h1>If anyone can move TooGeneral from Engine filder into PlatformerEngine/TooGeneral folder and make it work, that would be amazing!</h1>