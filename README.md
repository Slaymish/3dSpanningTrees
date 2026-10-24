# 3dSpanningTrees

Takes an OBJ file and returns its minimum spanning tree. The graph comes from the vertices and edges in the file, Kruskal's algorithm finds the tree, and the result is written back out as OBJ.

Built for Blender work. Export a mesh with the faces deleted, run it through this, and you get the spanning structure as geometry to skin or extrude.

![A spanning tree generated from a mesh](https://github.com/Slaymish/3dSpanningTrees/assets/21288505/3c9e87c3-d31d-4b37-8b8e-fd2ac10693fb)

## Using it

Grab the latest release, or run it from a checkout:

```bash
chmod +x run.command
./run.command
```

`run.command` compiles `src/main/*.java` with `javac` and runs `main.SpanningTrees`. It needs a Java runtime and a JDK, since it compiles on every run. `run.bat` is the Windows equivalent.

From Blender, delete the faces first: enter edit mode, select the mesh, use Delete Only Faces. Otherwise the importer sees faces where it wants edges.

There is also a Blender add-on in the releases, for doing the same thing without leaving Blender.

## State

Written in June 2023 for a mesh processing idea and left alone since. The core is four small classes: `Graph`, `Edge`, `Node` and `UnionFind`. The compiled classes and the IntelliJ `out/` directory are committed from the last build, which I would take out of the repo if I picked it up again.

MIT licensed.
