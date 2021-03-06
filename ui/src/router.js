import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import Dashboard from '@/pages/Dashboard'
import I18n from '@/modules/I18n'
import I18nLocale from '@/modules/I18nLocale'
import ResourceGroup from '@/modules/ResourceGroup'
import ResourceLocale from '@/modules/ResourceLocale'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/dashboard/',
    name: 'Dashboard',
    component: Dashboard,
    children: [
      {
        path: 'resource/group',
        name: 'ResourceGroup',
        component: ResourceGroup
      },
      {
        path: 'resource/:group/locale',
        name: 'ResourceLocale',
        component: ResourceLocale
      },
      {
        path: 'i18n/:group',
        name: 'I18n',
        component: I18n
      },
      {
        path: 'i18n/:group/:locale',
        name: 'I18nLocale',
        component: I18nLocale
      }
    ]
  },
    {
      path: '/',
      name: 'Login',
      component: Login
    }]
})
