package org.hs5tb.groospin.common

import net.bican.wordpress.FilterPost
import net.bican.wordpress.Post
import net.bican.wordpress.Wordpress

/**
 * Created by Alberto on 29-Jun-16.
 */
class WP {
    String url
    String login
    String password

    Wordpress wp

    WP(String url, String login, String password) {
        this.url = url
        this.login = login
        this.password = password
        wp = new Wordpress(login, password, url)
    }

    void printChildren(int id) {
        wp.getPosts(new FilterPost(post_type: "page")).each { Post page ->
            if (page.post_parent == id) {
                println "${page.post_id}:${page.getPost_title()}"
            }
        }
    }
    void deleteChildren(int id) {
        wp.getPosts(new FilterPost(post_type: "page")).each { Post page ->
            if (page.post_parent == id) {
                println "[-] Eliminando pagina ${page.post_id}"
                wp.deletePost(page.post_id)
            }
        }
    }

    Integer createPage(String title, String content, Integer parentId) {
        Post p = new Post()
        p.setPost_type("page")
        p.setPost_status("publish");
        p.setPost_title(title)
        p.setPost_content(content);
        if (parentId) p.setPost_parent(parentId)
        Integer pageId = wp.newPost(p);
        println "[+] Creando pagina ${pageId}"
        return pageId
    }
}
