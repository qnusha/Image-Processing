Hi! This is our Image Processing Program that we made for Assignments 4 and 5.

This code was primarily written by Anusha Bandaru and Jackson Green. The exception to this is the
ImageUtil.java file, which is based off of what was provided to us at the start of the project.

colorBlock.ppm, sunset.ppm, and tester.ppm were created by Anusha Bandaru in the GIMP paint editor,
and she gives permission to use them in this project.

koala.jpeg is from pexels (https://www.pexels.com/photo/close-up-photo-of-grey-koala-bear-2590768/)
- they offer free stock images that we can use for personal use. https://www.pexels.com/license/

RES FOLDER: Contains all of the images used in testing, as well as a class diagram.

SRC FOLDER: 
- Main.java
   - Contains a single function that can be run. This will cause the program to run within the
   Command Line.
   - In order to see all implemented arguments, enter "help" into the command line after running
   the program.
   - In order to create a vertically flipped and darkened version of the tester.ppm image that has
   had a red-component grayscale applied, enter the following commands after running the program.
      - load res/testImages/tester.ppm test
      - vertical-flip test upsidedown
      - brighten -30 upsidedown upsidedark
      - red-component upsidedark redupsidedark
      - save res/testImages/exampleResultTester.ppm redupsidedark

CONTROLLER PACKAGE:
- INTERFACE ImageController.java
   - Interface for Controllers that should preform the image processing operations on any Image
- CLASS ImageControllerImpl.java implements ImageController.java
   - Implements the ImageController interface, which interprets the user-inputted commands and
   calls the operations on the Image that is stored in a hashmap within the model.
- INTERFACE CreateImageType.java
   - This class takes in an Image as its only field and performs an operation that saves the image
   as a correct image format.
- CLASS CreatePPM.java implements CreateImageType.java
   - When save is called on this class, the Image will be converted to the proper PPM format and be
   saved to the given image path.
- CLASS CreateImageIO.java implements CreateImageType.java
   - When save is called on this class, the Image will be converted to any image type supported
   by ImageIO and be saved to the given image path.
- INTERFACE Load.java
      - Handles loading image files into images usable by this program.
- CLASS PPMUtil.java implements Load.java
   - Loads a ppm file into an image usable by this program.
- CLASS ImageIOUtil.java implements Load.java
   - Loads any image type supported by ImageIO into an image usable by this program.
- CLASS GraphicalImageController.java implements ImageController.java
   - ImageController to be used in tandem with GraphicalImageView.

MODEL PACKAGE:
- CLASS Pixel.java
   - Used to represent a singular pixel with its red, green, and blue values. Includes methods to
   retrieve this data.
- INTERFACE Image.java
   - This class represents the details of an image format.
- CLASS SimpleImage.java implements Image.java
   - Represents the general details that most image formats have and contains fields that specify
   the image's width, height, and 2D array of pixels.
- INTERFACE ImageManager.java
   - Interface that can be implemented to create a management system for Images. This is the primary
   object used as the Model within the program.
- CLASS SimpleImageManager.java implements ImageManager.java
   - A manager for SimpleImages that uses a HashMap to keep track of its pictures.

MODEL.OPS PACKAGE:
- INTERFACE ImageOperation.java
   - Interface that can be implemented to create function objects. These contain operations that
   can be run on Images.
- CLASS Brighten.java implements ImageOperation.java
   - Brightens the entire image by an amount given at initialization. Will darken if initialized
   with a negative number.
- CLASS HorizontalFlip.java implements ImageOperation.java
   - Flips an image horizontally.
- CLASS VerticalFlip.java implements ImageOperation.java
   - Flips an image vertically.
- CLASS RedComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the amount of red in the pixel.
- CLASS GreenComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the amount of green in the pixel.
- CLASS BlueComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the amount of blue in the pixel.
- CLASS LumaComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the luma value of the pixel.
- CLASS IntensityComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the intensity value of the pixel.
- CLASS ValueComponent.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the greatest value of the pixel.
- ABSTRACT CLASS KernelOperation.java implements ImageOperation.java
   - Preforms an operation on an image using a kernel.
- CLASS GaussianBlur.java implements ImageOperation.java
   - Change the RGB Values of every pixel in the picture using the Gaussian Blur kernel values
   and the kernel operation.
- CLASS Sharpen.java implements ImageOperation.java
   - Change the RGB Values of every pixel in the picture using the sharpening kernel values
   and the kernel operation.
- CLASS SepiaFilter.java implements ImageOperation.java
   - Sets the RGB Values of every pixel in the picture to match the sepia value of the pixel.

MODEL.HISTOGRAM PACKAGE:
- INTERFACE Histogram.java
   - Interface for Histograms.
- CLASS RedHistogram.java implements Histogram.java
   - Histogram generator that uses the Red Component of Pixels.

VIEW PACKAGE:
- INTERFACE ImageView.java
   - Interface that supports the visual operations of Image processing
- CLASS SimpleImageView.java implements ImageView.java
   - Renders any message (given by the controller) to the output. Does not include field that takes
   in model because it is not necessary at the moment (only its message-sending capabilities are
   called).
- CLASS GraphicalImageView.java implements ImageView.java
   - Uses Swing Components to render a graphical version of the program.

CHANGES:
- ImageView interface now contains an updateImage function