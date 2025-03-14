import request from '@/utils/request'

// 查询所有境外学校列表
export function listAllUniversities(query) {
  return request({
    url: '/oversea/university/listAll',
    method: 'get',
    params: query
  })
}

// 查询境外学校详细信息
export function getUniversityById(universityId) {
  return request({
    url: '/oversea/university/detail',
    method: 'get',
    params: { universityId }
  })
}

// 添加境外学校
export function addUniversity(data) {
  return request({
    url: '/oversea/university/add',
    method: 'post',
    data: data
  })
}

// 更新境外学校信息
export function updateUniversity(data) {
  return request({
    url: '/oversea/university/update',
    method: 'post',
    data: data
  })
}

// 删除境外学校
export function deleteUniversity(universityId) {
  return request({
    url: '/oversea/university/delete',
    method: 'get',
    params: { universityId }
  })
}

// 添加专业
export function addMajor(data) {
  return request({
    url: '/oversea/major/add',
    method: 'post',
    data: data
  })
}

// 获取学校的专业列表
export function listMajorsByUniversityId(universityId) {
  return request({
    url: '/oversea/major/listByUniversity',
    method: 'get',
    params: { universityId }
  })
}
