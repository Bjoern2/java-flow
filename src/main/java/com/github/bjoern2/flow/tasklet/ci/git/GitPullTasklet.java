package com.github.bjoern2.flow.tasklet.ci.git;

import java.util.Collection;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;

import com.github.bjoern2.flow.model.Tasklet;

public class GitPullTasklet implements Tasklet {

    @Override
    public String execute() throws Throwable {
        
        Collection<Ref> refs = Git.lsRemoteRepository()
                .setHeads(true)
                .setTags(true)
                .setRemote("https://github.com/Bjoern2/java-flow.git")
                .call();
        
        
        for (Ref ref : refs) {
            System.out.println(ref);
        }
        
        
        
//        FileRepositoryBuilder b = new FileRepositoryBuilder();
//        b.setGitDir(new File("C:/temp/git/test"));
//        b.readEnvironment();
//        b.findGitDir();
////        b.setWorkTree(new File("C:/temp/git/test"));
//        b.setBare();
//        
//
//        Repository repo = b.build();
//        Git git = new Git(repo);
//        
//        StoredConfig config = git.getRepository().getConfig();
//        config.setString("remote", "origin", "url", "https://github.com/Bjoern2/java-flow.git");
//        config.save();
//        
//
//        ObjectId id = repo.resolve("refs/remotes/origin/master");
//        
//        FetchCommand fetch = git.fetch();
//        FetchResult result = fetch.call();
//        DiffCommand diff = git.diff();
//        List<DiffEntry> diffEntries = diff.call();
//        
//        for (DiffEntry diffEntry : diffEntries) {
//            System.out.println(diffEntry.getNewId());
//        }
        return "SUCCESS";
    }
}
