import request from '@/utils/request'

// 查询所有专业列表
export function listAllMajors(query) {
  return request({
    url: '/oversea/inMajor/listAll',
    method: 'get',
    params: query
  })
}

// 查询专业详细信息
export function getMajorById(majorId) {
  return request({
    url: '/oversea/inMajor/detail',
    method: 'get',
    params: { majorId }
  })
}

// 添加专业
export function addMajor(data) {
  return request({
    url: '/oversea/inMajor/add',
    method: 'post',
    data: data
  })
}

// 更新专业信息
export function updateMajor(data) {
  return request({
    url: '/oversea/inMajor/update',
    method: 'post',
    data: data
  })
}

// 删除专业
export function deleteMajor(majorId) {
  return request({
    url: '/oversea/inMajor/delete',
    method: 'get',
    params: { majorId }
  })
}
