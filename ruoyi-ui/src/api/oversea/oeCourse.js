import request from '@/utils/request'

// 查询所有课程列表
export function listAllCourses(query) {
  return request({
    url: '/oversea/course/listAll',
    method: 'get',
    params: query
  })
}

export function getCourseByRApplicationId(rApplicationId) {
  return request({
    url: '/oversea/course/listByRApplicationId',
    method: 'get',
    params: { rApplicationId }
  })
}

// 查询课程详细信息
export function getCourseById(courseId) {
  return request({
    url: '/oversea/course/detail',
    method: 'get',
    params: { courseId }
  })
}

// 添加课程
export function addCourse(data) {
  return request({
    url: '/oversea/course/add',
    method: 'post',
    data: data
  })
}

// 更新课程信息
export function updateCourse(data) {
  return request({
    url: '/oversea/course/update',
    method: 'post',
    data: data
  })
}

// 删除课程
export function deleteCourse(courseId) {
  return request({
    url: '/oversea/course/delete',
    method: 'get',
    params: { courseId }
  })
}
