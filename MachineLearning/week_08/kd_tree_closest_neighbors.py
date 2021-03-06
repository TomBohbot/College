# -*- coding: utf-8 -*-
"""KD_Tree_Closest_Neighbors.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1fP8BvD3LVr1qpAA5SPN_XmpNig35JLyb
"""

# Necessary import statements:
import collections
import operator

# tuple required to structure nodes:
node = collections.namedtuple("node", ["value", "left", "right"])

def build_kdtree(points):
  return recursively_build_kdtree(points, 0)

def recursively_build_kdtree(points, depth):
  if len(points) == 0:
    return
  else:
    # recursively add the upper and lower half of points.
    num_dimensions = len(points[0])                              
    points.sort(key=operator.itemgetter(depth % num_dimensions)) # find upper and lower half of points through sorting
    median = len(points) // 2                                    # find median to access lower and upper half in constant time
    return node(
        value = points[median],
        left = recursively_build_kdtree(points[:median], depth+1),   # add the lower half of points
        right= recursively_build_kdtree(points[median+1:], depth+1), # add the upper half of points
    )

"""Show functionality of kd tree with self-generated small dataset"""

# 2 dimensional k-d tree:
points = [ (1, 2, 3), (2, 3, 3), (3, 4, 3), (4, 5, 3) ]
tree = build_kdtree(points)
print("2 dimensional k-d tree:", tree)

# 3 dimensional k-d tree:
points = [ (1, 2, 3), (2, 3, 4), (3, 4, 5), (4, 5, 6) ]
tree = build_kdtree(points)
print("3 dimensional k-d tree:", tree)

# 4 dimensional k-d tree:
points = [ (1, 2, 3, 1), (2, 3, 4, 1), (3, 4, 5, 1), (4, 5, 6, 1) ]
tree = build_kdtree(points)
print("4 dimensional k-d tree:", tree)

"""Show functionality from iris dataset"""

from sklearn.datasets import load_iris
iris = load_iris()

# 2 dimensional k-d tree:
X = iris.data[:, 2:] 
y = iris.target
points = []
for elem in X:
  first, second = elem[0], elem[1]
  cur_tuple = (first, second)
  points.append(cur_tuple)
tree = build_kdtree(points)
print("2d k-d tree:", tree)

# 3 dimensional k-d tree:
X = iris.data[:, 1:] 
y = iris.target
points = []
for elem in X:
  first, second, third = elem[0], elem[1], elem[2]
  cur_tuple = (first, second, third)
  points.append(cur_tuple)
tree = build_kdtree(points)
print("3d k-d tree:", tree)

# 4 dimensional k-d tree:
X = iris.data
y = iris.target
points = []
for elem in X:
  first, second, third, fourth = elem[0], elem[1], elem[2], elem[3]
  cur_tuple = (first, second, third, fourth)
  points.append(cur_tuple)
tree = build_kdtree(points)
print("4d k-d tree:", tree)

"""Extra Credit: Find Nearest Neigbor"""

# helper function for find nearest neighbor to find distnace between two points:
def calc_distance(X, Y):
    return sum((i-j)**2 for i, j in zip(X, Y))

# tuple required for the function:
min_distance = collections.namedtuple("min_distance", ["point", "distance"])

def find_nearest_neighbor(tree, point):
  closest_point = None
  num_dimensions = len(point)
  def recursively_find_nearest_neighbor(tree, depth):
    # base case:
    if tree is None:
        return
    else:
      nonlocal closest_point
      # check if current point beats the current closest point:
      distance = calc_distance(tree.value, point)
      if closest_point is None or distance < closest_point.distance:
          closest_point = min_distance(tree.value, distance)
      axis = depth % num_dimensions
      diff = point[axis] - tree.value[axis]
      # find left and right child for recursive call:
      if diff <= 0:
          left, right = tree.left, tree.right
      else:
          left, rigth = tree.right, tree.left
      # recursively check if there is a closer point:
      recursively_find_nearest_neighbor(left, depth+1)
      if diff**2 < closest_point.distance:
          recursively_find_nearest_neighbor(right, depth+1)
  recursively_find_nearest_neighbor(tree, 0)
  return closest_point.point

"""Driver to show finding nearest neighbor works"""

# Driver:
points = [ (1, 2), (3, 4), (8, 9), (10, 12) ]
kd_tree = build_kdtree(points)
print(find_nearest_neighbor(kd_tree, (1, 2)) )
print(find_nearest_neighbor(kd_tree, (10, 11)) )
print(find_nearest_neighbor(kd_tree, (6, 7)) )