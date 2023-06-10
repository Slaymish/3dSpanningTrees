bl_info = {
    "name": "Kruskal's Algorithm",
    "blender": (3, 5, 0),
    "category": "Object",
}

import bpy
import bmesh
from collections import defaultdict

class KruskalOperator(bpy.types.Operator):
    """Kruskal's Algorithm Operator"""
    bl_idname = "object.kruskal_operator"
    bl_label = "Generate Minimum Spanning Tree"
    bl_options = {'REGISTER', 'UNDO'}

    def execute(self, context):
        obj = bpy.context.active_object
        if obj is None:
            self.report({'ERROR'}, "No object selected")
            return {'CANCELLED'}
        if obj.type != 'MESH':
            self.report({'ERROR'}, "Selected object is not a Mesh type")
            return {'CANCELLED'}

        mesh = obj.data

        # Kruskal's algorithm
        parent, rank = dict(), dict()

        def make_set(vert):
            parent[vert] = vert
            rank[vert] = 0

        def find(vert):
            if parent[vert] != vert:
                parent[vert] = find(parent[vert])
            return parent[vert]

        def union(vert1, vert2):
            root1 = find(vert1)
            root2 = find(vert2)
            if root1 != root2:
                if rank[root1] > rank[root2]:
                    parent[root2] = root1
                else:
                    parent[root1] = root2
                if rank[root1] == rank[root2]: 
                    rank[root2] += 1

        def kruskal(graph):
            for vert in graph['vertices']:
                make_set(vert)
            minimum_spanning_tree = set()
            edges = list(graph['edges'])
            edges.sort()
            for edge in edges:
                weight, vert1, vert2 = edge
                if find(vert1) != find(vert2):
                    union(vert1, vert2)
                    minimum_spanning_tree.add(edge)
            return sorted(minimum_spanning_tree)

        verts_loc = [v.co for v in obj.data.vertices]
        edges = [(edge.vertices[0], edge.vertices[1]) for edge in obj.data.edges]
        graph = {'vertices': list(range(len(verts_loc))),
                 'edges': set(),}
        for (vert1, vert2) in edges:
            graph['edges'].add(((verts_loc[vert1] - verts_loc[vert2]).length, vert1, vert2))


        minimum_spanning_tree = kruskal(graph)

        # Create new mesh and object
        new_mesh = bpy.data.meshes.new('mst_mesh')
        new_object = bpy.data.objects.new('mst_object', new_mesh)

        # Link new object to the scene
        bpy.context.collection.objects.link(new_object)

        # Create mesh from pydata
        new_mesh.from_pydata(verts_loc, [edge[1:] for edge in minimum_spanning_tree], [])
        new_mesh.update()

        return {'FINISHED'}


class KruskalPanel(bpy.types.Panel):
    """Kruskal's Algorithm Panel"""
    bl_label = "Kruskal's Algorithm"
    bl_idname = "OBJECT_PT_kruskal"
    bl_space_type = 'VIEW_3D'
    bl_region_type = 'UI'
    bl_category = "Tool"

    def draw(self, context):
        layout = self.layout
        layout.operator(KruskalOperator.bl_idname)

def register():
    bpy.utils.register_class(KruskalOperator)
    bpy.utils.register_class(KruskalPanel)

def unregister():
    bpy.utils.unregister_class(KruskalOperator)
    bpy.utils.unregister_class(KruskalPanel)

if __name__ == "__main__":
    register()
