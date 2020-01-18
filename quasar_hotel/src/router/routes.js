
const routes = [
  {
    path: '/',
    component: () => import('layouts/guest.vue'),
    children: [
      { path: '', component: () => import('pages/login.vue') }
    ]
  },
  {
    path: '/home',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/lihatdata',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Lihatdata.vue') }
    ]
  },
  {
    path: '/input',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/inputdata.vue') }
    ]
  },
  {
    path: '/datahotel',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/datahotel.vue') }
    ]
  }
]
// Always leave this as last one

if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '/inputdata',
    component: () => import('pages/Index.vue')
  })
}
export default routes
