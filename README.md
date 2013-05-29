Objects so far

QBox (Box):
    A simple box,
    has x and y and width and hegith.
    Can call draw or drawBox to see what it looks like

QPlatform (Platform):
    Extension of QBox,
    contains both and image and color.
    If image is not loaded then draw function will draw whatever you set color to,
    default color is black

QMBox (Moving Box):
    Extension of QBox. A box which has vectors.
    Pretty useless by itself.

QMControls (Movement Controls):
    Extension of QMBox. Introduces controls for the movement box.

QBBox (Bounding Box):
    Extension of QBox. A box with a bounding box
    Contains offsets which can be used for more realistic like collision detection.

QBPlatform (Bounding Platform):
    Extension of QBBox. Contains image only.
    Draw method will draw the image to canvas.
    Please make sure to load the image beforehand.

QBMBox (Bounding Movement Box):
    Extension of QBBox. A Bounding box which has vectors.
    Pretty useless by itself.

QBMControls (Bounding Movement Controls):
    Extension of QBMBox. Introduces controls for the movement bounding box.



Others:

Main:
    Contains brains of the game, don't touch it if you can help it.

Game:
    Contains functions which have to be populated for the game to work.
    Those functions are being called by Main. Much more simpler to read and understand.

QConstants:
    Has constant values that can be used.
