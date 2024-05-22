# Lecture Code
This repository contains all files we use/make during lectures.
After each lecture we will update the project with the relevant files.

## How do I keep my local changes when new lecture notes are added to this file?
If you make changes to this repo, there might become conflicts if both you and the lecturer have changed the same files, and you then try to pull these changes to update your clone of the repo.

It all depends on what you intend to do with the code:
* [I want to make changes to the code and store these in gitlab.](#1-create-a-separate-project-with-changes)
* [I want to make changes to the code but only store them locally.](#2-use-pull-and-manually-deal-with-conflicts)
* [I want to make changes to the code but not store them.](#3-delete-the-clone-and-re-clone)
* [I don't want to make changes to the code.](#4-dont-make-changes)


### 1. Create a separate project with changes
1. Fork and clone or directly clone this repo.
2. Create your own project that is intended for changes. This can be a repo on gitlab that you clone, or it can be just a local file if you don't intend on storing it on gitlab.
3. Copy over the files you want from your clone of the lecture_code-repo, and paste them into the change-repo. You can push these freely if you created a repo.
4. When you want to update your lecture_code repo, use the command ``git pull`` in your terminal to pull new changes from the lecture_code repo. **If you created your own repo**, go into this repo on gitlab and press the button that says "Update fork" first.
5. Copy over the update files you want into your change-repo. You now have no conflicts and get to keep your own changes without problem.

### 2. Use pull and manually deal with conflicts.
**If you want to push your changes to gitlab, use the previous method. This method will make it hard to do so.**

The previous method used the command ``git pull``. No conflicts arose, because no changes had been made to the repo. These were kept in a seperate project. **If you don't intend on pushing your changes to git, you can save yourself the trouble of copying files by using ``git pull`` and then manually dealing with the conflicts.** When you pull files that cause conflict, you will have to do one of two things first:

#### Commit or stash
**Commiting** should be familiar by now. This is what we do before we can push any changes to our gitlab repo.
1. **If you forked this repo**, go into your repo on gitlab and press the button that says "Update fork" first.
2. Save all the files you made changes to locally in you IDE.
3. Use the command ``git add .`` in your terminal to "stage" the changes. (Most IDEs have built in functionality to stage changes as well.)
4. Use the command ``git commit -m "YOUR COMMENT HERE"`` to commit the changes. **DO NOT PUSH**. This may cause a lot of issues. If you want to push changes, use the method above.
5. Use the command ``git pull`` to get the changes from the cloned repo. Conflicts might arise here, and you have to manually choose if you want to keep one version of the file or both versions.

**Stashing** means "putting away" you local changes so you can work on other things and then get these changes back when you need them. This method essentially does the same as commiting.
1. **If you forked this repo**, go into your repo on gitlab and press the button that says "Update fork" first.
2. Save all files you made changes to locally in you IDE.
3. Use the command ``git stash`` in your terminal to stash away the changes. Your project will revert back to the last commit, and your changes will temporarily disappear.
4. Use the command ``git pull`` to get the changes from the cloned repo.
5. Use the command ``git stash pop`` to reinstate your local changes. Conflicts might arise here, and you have to manually choose if you want to keep one version of the file or both versions. **DO NOT PUSH**. This may cause a lot of issues. If you want to push changes, use the method above.

### 3. Delete the clone and re-clone
**If you don't feel the need to keep your changes to the lecture code**, you can simply delete your clone whenever new updates are added. Then, you can clone the project over again and take it from there.

### 4. Don't make changes
**If you don't intend on making any changes to the lecture code**, but you want to have a local save, there will be no conflict issues and you can just use ``git pull`` in your terminal to update your clone (or use method 3).
