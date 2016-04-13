author : bikesh kawan

instruction


1. All the code works fine and compile properly and  app runs smoothly in my computer.
2. You might need to import appcompat_v7 library  to the eclipse project as i have used Action Bar
	then you might need to change the class path in project.properties like
	 android.library.reference.1=../appcompat_v7 
3. then right click on the project and click on properties then go to Android and 
	 add appcompat_v7 library from the eclipse project and clean project
	 
	 4 .If it did not work you might need to add jar to do this, 
	 Right click on project then go to >properties>java build path>library>addjar
	 Then add android-support-v7-appcompat.jar from the appcompat_v7>libs
	 then go to order and Export and check android-support-v7-appcompat.jar/libs and save
	 
	 Hope this will work.
	 
	 I have also included MyDownloadMovieApp.apk file in CD in worst case you can test .apk file in mobile devices or test online
	 