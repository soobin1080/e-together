<template>
<div>
<v-navigation-drawer v-model="sidebar" app>
      <v-list>
        <v-list-item
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">
          <v-list-item-action>           
          </v-list-item-action>
          <v-list-item-content>{{ item.title }}</v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <span class="hidden-sm-and-up">
        <v-app-bar-nav-icon @click="sidebar = !sidebar">
            <v-icon>menu</v-icon>
        </v-app-bar-nav-icon>
      </span>
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">
          {{ appTitle }}
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn
          text
          v-for="item in menuItems"
          :key="item.title"
          :to="item.path">         
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>
</div>
  <!-- <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <router-link to="/"><b-navbar-brand>Blog</b-navbar-brand></router-link>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item><router-link to="/post">Post</router-link></b-nav-item>
          <b-nav-item><router-link to="/portfolio">Portfolio</router-link></b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div> -->
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      appTitle: 'Blog',
      sidebar: false,
      menuItems: [
          { title: 'Home', path: '/'},
          { title: 'Post', path: '/post'},
          { title: 'Portfolio', path: '/portfolio'}
     ]
    }
  }
};
</script>
