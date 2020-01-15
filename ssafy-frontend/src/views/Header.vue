<template>
<div>
<v-navigation-drawer v-model="sidebar" fixed temporary>
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
          <strong>{{ appTitle }}</strong>
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
