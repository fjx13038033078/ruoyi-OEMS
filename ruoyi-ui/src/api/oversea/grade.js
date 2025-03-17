import request from '@/utils/request'

export function listAllGrades(query) {
  return request({
    url: '/oversea/grade/listAll',
    method: 'get',
    params: query
  })
}

export function getGradeById(gradeId) {
  return request({
    url: '/oversea/grade/detail',
    method: 'get',
    params: { gradeId }
  })
}

export function addGrade(data) {
  return request({
    url: '/oversea/grade/add',
    method: 'post',
    data: data
  })
}

export function updateGrade(data) {
  return request({
    url: '/oversea/grade/update',
    method: 'post',
    data: data
  })
}

export function deleteGrade(gradeId) {
  return request({
    url: '/oversea/grade/delete',
    method: 'get',
    params: { gradeId }
  })
}
