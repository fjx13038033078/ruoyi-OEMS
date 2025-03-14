import request from '@/utils/request'

export function listAllMajors() {
  return request({
    url: '/oversea/major/listAll',
    method: 'get'
  })
}

export function listMajorsByUniversityId(universityId) {
  return request({
    url: '/oversea/major/listByUniversity',
    method: 'get',
    params: { universityId }
  })
}

export function getMajorByRApplicationId(rApplicationId) {
  return request({
    url: '/oversea/major/listByRApplicationId',
    method: 'get',
    params: { rApplicationId }
  })
}

export function getMajorDetail(majorId) {
  return request({
    url: '/oversea/major/detail',
    method: 'get',
    params: { majorId }
  })
}

export function addMajor(data) {
  return request({
    url: '/oversea/major/add',
    method: 'post',
    data
  })
}

export function updateMajor(data) {
  return request({
    url: '/oversea/major/update',
    method: 'post',
    data
  })
}

export function deleteMajor(majorId) {
  return request({
    url: '/oversea/major/delete',
    method: 'get',
    params: { majorId }
  })
}
