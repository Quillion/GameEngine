<h1><b>Rules</b></h1>
<p>
ALL code must be commented. And all methods should have javadoc comments. (I am working on commenting my code now).
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
<b>QBox (Box):</b>
    A simple box,
    has x and y and width and hegith.
    Can call draw or drawBox to see what it looks like

<b>QBBox (Bounding Box):</b>
    Extension of QBox. A box with a bounding box
    Contains offsets which can be used for more realistic like collision detection.
</p>

<h2>Moving Shapes:</h2>

<p>
<b>QMBox (Moving Box):</b>
    Extension of QBox. A box which has vectors.
    Pretty useless by itself.

<b>QBMBox (Bounding Movement Box):</b>
    Extension of QBBox. A Bounding box which has vectors.
    Pretty useless by itself.
</p>

<h2>Extended Shapes:</h2>

<p>
<b>QMControls (Movement Controls):</b>
    Extension of QMBox. Introduces controls for the movement box.

<b>QBMControls (Bounding Movement Controls):</b>
    Extension of QBMBox. Introduces controls for the movement bounding box.
</p>

<h2>Basic Sprite:</h2>

<p>
<b>QPlatform (Platform):</b>
    Extension of QBox,
    contains both and image and color.
    If image is not loaded then draw function will draw whatever you set color to,
    default color is black.

<b>QBPlatform (Bounding Platform):</b>
    Extension of QBBox. Contains image only.
    Draw method will draw the image to canvas.
    Please make sure to load the image beforehand.

<b>QMCharacter (Moving Character):</b>
    This is a fuck up, and I got lazy to continue with this one.
    Will resume after I get to other ones.
</p>



<h2>Others:</h2>

<p>
<b>Main:</b>
    Contains brains of the game, don't touch it if you can help it.

<b>Game:</b>
    Contains functions which have to be populated for the game to work.
    Those functions are being called by Main. Much more simpler to read and understand.

<b>QConstants:</b>
    Has constant values that can be used.
</p>



<h2>Logic</h2>

<p>
<b>QCamera:</b>
    Camera controls. Using draw method here is better than using generic draw provided by the object.

<b>QEngine:</b>
    Collision detection. This will be used to check for collision between all the objects, be it platformer or crawler.

<b>QImageExtractor</b>
    Give it an Image, and then you can use it to slice and dice the image and get the tinier pieces of it.

<b>QImageProcessor</b>
    Used to glue the images together however you would like.
</p>

<h1>If anyone can move TooGeneral into platformer folder and make it work, that would be amazing</h1>