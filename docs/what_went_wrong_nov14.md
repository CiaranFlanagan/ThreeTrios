Issue 1: jar didn't work
-
This took so much time to understand, and the bug is so intricate. It resolves around the
directory being marked as test sources root. Our test files were in the test directory, which
makes sense. When you create a jar, it asks to include tests. THIS DOESNT MEAN JUST JUNIT TESTS.
This means that code. So, if I want to keep that stuff in the test dir, when creating the jar, I
need to check that box. Also, src can't see test, but test can see src. That's why originally
the jar was in src, and it didn't even get the test files.

Part 2 3 hours later...

Files act weird after you make a jar. If we depend on a file during te running of our main,
stuff goes wrong when we make the jar.

Solution, lots of ChatGPT. It turns out we need a resources
root for files.
Then, we can find these when we build, but not in a jar. In a jar, files turn into input streams.
Now I love that I learned what these were before. Once I figured this out, just had to wrap in a
scanner and change all code to just cut out middle man and rely on scanners.



Issue 2: git
-
I'm not sure from when it started, but every time I committed there was this "changes by ciaran"
change list. What I didn't realize is that the changelist was because I didn't handle a merge
properly. Usually we just did accept right or left, but this time we had to actually merge, and
I didn't know how to do that the first couple times, so I'm not sure how that worked.

The fix is to add all those changes to the commit. I thought those were the changes from some
old version, but Changes by Ciaran has a change list with KC's version changes.

