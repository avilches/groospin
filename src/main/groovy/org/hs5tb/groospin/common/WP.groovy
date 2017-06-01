package org.hs5tb.groospin.common

import net.bican.wordpress.FilterPost
import net.bican.wordpress.Post
import net.bican.wordpress.Wordpress
import org.hs5tb.groospin.base.HyperSpin

/**
 * Created by Alberto on 29-Jun-16.
 */

WP2 wp = new WP2("http://hyperspin5tb.com/xmlrpc.php", "admin", "")
HyperSpin spin = new HyperSpin("D:/Games/RocketLauncher")
def pages = wp.listAllPages()
def titles = pages*.post_title.collect { it.toLowerCase() } as Set

spin.listSystemNames(true).each {
    String system ->
        //system = system.split(" ").collect { it.capitalize() }.join(" ")
        if (!(system.toLowerCase() in titles)) {
            println "$system -> ${system in titles}"
            wp.createPage(system, "[includeme file=\"/var/www/hs/static/website/system-${system}.html\"]", 123)
        }

}


class WP2 {
    String url
    String login
    String password

    Wordpress wp

    WP2(String url, String login, String password) {
        this.url = url
        this.login = login
        this.password = password
        wp = new Wordpress(login, password, url)
    }

    List<Post> listAllPages() {
        wp.getPosts(new FilterPost(post_type: "page", number:999999))
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
