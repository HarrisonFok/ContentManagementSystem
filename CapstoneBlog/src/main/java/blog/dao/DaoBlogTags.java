/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.dao;

import blog.dto.Blog;
import blog.dto.BlogTags;
import blog.dto.Tag;
import java.util.List;

/**
 *
 * @author Joshua Martel
 */
public interface DaoBlogTags {
    
    public BlogTags addTag(BlogTags bTag);
    public boolean removeTagFromBlog(BlogTags bTag);
    public List<Blog> getAllBlogsWithTag(int tagID);
    public List<Tag> getAllTagsForBlog(int blogID);
    public List<BlogTags> getAllBlogTags();
    
}
