From Pigi Kouki:
>I searched online and find the following useful link that helped me solve the problem
>https://groups.google.com/forum/#!topic/h2-database/XeFtWY_vvBQ
>So I needed to download the source code for h2-1.2.126-sources.jar (the version of h2 that PSL uses), change the line in HashBase.java file 
>        maxSize = (int) (len * MAX_LOAD / 100L); 
>to 
>        maxSize = (int) (((long)len) * MAX_LOAD / 100L); 
>and then create the new jar of this library and included it in the project.