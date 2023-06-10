# 3dSpanningTrees
<p align="center">Takes an OBJ file as input and returns the Minimum Spanning Tree (MST) of it</p>

<p align="center">
  <img src="https://github.com/Slaymish/3dSpanningTrees/assets/21288505/3c9e87c3-d31d-4b37-8b8e-fd2ac10693fb">
</p>

3dSpanningTrees uses Kruskal's algorithm to find the Minimum Spanning Tree (MST) of a graph. The graph is loaded from vertices and edges contained in an OBJ file. 

The output is an OBJ file consisting of the MST of the input graph.

**Note:** When exporting the OBJ from Blender, select the mesh in edit mode and choose 'Delete Only Face'. This ensures that only edges and vertices are taken into account.

## Prerequisites

Before you begin, ensure you have the following installed:
- [Java Runtime Environment](https://www.oracle.com/java/technologies/downloads/)

## Installation

Follow the steps below to get a copy of the project up and running on your local machine for development and testing purposes:

```bash
git clone https://github.com/Slaymish/3dSpanningTrees
cd 3dSpanningTrees
```

Blender Addon Installation (MSTAdddon.py)
The provided Blender addon allows you to generate MSTs directly in Blender. The 'Generate' button is located in the Tool tab of the right panel.

## Usage
You can run the application using the provided executables.

**For MacOS**
Run the following command to grant execute permissions to the script:
```
chmod +x run.command
```

Then, double click run.command to run the application.

**For Windows**
Simply double click run.bat to run the application.
