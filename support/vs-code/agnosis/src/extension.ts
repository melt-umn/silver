// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';
import { WorkspaceFolder, DebugConfiguration, ProviderResult, CancellationToken } from 'vscode';
import * as fs from 'fs';
import * as path from 'path';
import * as net from 'net';

let server: net.Server; 

// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed
export function activate(context: vscode.ExtensionContext) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	console.log('Congratulations, your extension "agnosis" is now active! And silver is on!');
	const terminal = vscode.window.createTerminal('Silver');
	// pop out the new terminal
	terminal.show();
	// listen to port 24352
	

	

	let currentPath = vscode.workspace.workspaceFolders?.[0].uri.path || '';
	let current_path_split = currentPath.split('/');

	

	// read the .debugger_communicator.json file, into a dictionary, it's under workspace folder

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with registerCommand
	// The commandId parameter must match the command field in package.json
	let disposable = vscode.commands.registerCommand('agnosis.helloWorld', () => {
		// The code you place here will be executed every time your command is executed
		// Display a message box to the user
		vscode.window.showInformationMessage('hheeeeeeeeeelo');
	});

	let launchSilver_dispoable = vscode.commands.registerCommand('agnosis.launchSilver', () => {
		vscode.window.showInformationMessage('Launching Silver...');
		// run the command of silver in terminal
		terminal.show();
		vscode.window.showInformationMessage(currentPath);
		
	});
	let run_compiler = vscode.commands.registerCommand('agnosis.RunCompile', () => {
		vscode.window.showInformationMessage('Running Compiler...');
		terminal.sendText(`./silver-compile --force-origins --clean`);
	});
	

	let run_traslator = vscode.commands.registerCommand('agnosis.RunSilver', () => {
		vscode.window.showInformationMessage('Running Translator...');
		terminal.sendText(`./silver-compile --force-origins --clean`);
		// get current open tab file name
		let working_file = vscode.window.activeTextEditor?.document.fileName || '';
		let working_file_split = working_file.split('/');
		working_file = working_file_split[working_file_split.length - 1];
		let direct_folder = current_path_split[current_path_split.length - 1];
		
		vscode.window.showInformationMessage(working_file);
		terminal.sendText(`java -jar ${direct_folder}.jar ${working_file}`);
	});


	let HighlightSilver = vscode.commands.registerCommand('agnosis.HighlightSilver', () => {
		let currentPath = vscode.workspace.workspaceFolders ? vscode.workspace.workspaceFolders[0].uri.fsPath : '';
		let debugger_communicator_path = path.join(currentPath, '.debugger_communicator.json');
	
		interface FileHighlightInfo {
			file_path: string;
			line_begin: number;
			line_end: number;
		}
	
		fs.readFile(debugger_communicator_path, 'utf8', (err, data) => {
			if (err) {
				console.error(err);
				return;
			}
			try {
				let filesToHighlightDic: FileHighlightInfo = JSON.parse(data);
				// if line_begin and line_end are the same value, make line_end + 1
				if (filesToHighlightDic.line_begin === filesToHighlightDic.line_end) {
					filesToHighlightDic.line_end++;
				}
	
				let viewColumn: vscode.ViewColumn;
	
				// Attempt to find an already opened document
				const openedTextEditor = vscode.window.visibleTextEditors.find(editor => editor.document.uri.fsPath === filesToHighlightDic.file_path);
	
				if (openedTextEditor) {
					// If the file is already open, use the existing view column
					viewColumn = openedTextEditor.viewColumn ?? vscode.ViewColumn.One;
					highlightRange(openedTextEditor, filesToHighlightDic);
				} else {
					// Determine view column if not already open
					viewColumn = vscode.window.visibleTextEditors.length > 1 ? vscode.window.activeTextEditor?.viewColumn ?? vscode.ViewColumn.One : vscode.ViewColumn.Beside;
					vscode.workspace.openTextDocument(filesToHighlightDic.file_path).then(doc => {
						vscode.window.showTextDocument(doc, viewColumn).then(editor => {
							highlightRange(editor, filesToHighlightDic);
						});
					});
				}
			} catch (parseErr) {
				console.error('Error parsing JSON:', parseErr);
			}
		});
	
		function highlightRange(editor: vscode.TextEditor, highlightInfo: FileHighlightInfo) {
			editor.selection = new vscode.Selection(
				new vscode.Position(highlightInfo.line_begin - 1, 0),
				new vscode.Position(highlightInfo.line_end - 1, 0)
			);
			editor.revealRange(editor.selection);
		}
	});
	

    let currentPanel: vscode.WebviewPanel | undefined = undefined;

    const showAttributeValuesHtml = vscode.commands.registerCommand('agnosis.showAttributeValuesHtml', () => {
        if (!currentPanel) {
            currentPanel = vscode.window.createWebviewPanel(
                'attributeValues',
                'Attribute Values',
                vscode.ViewColumn.One,
                { enableScripts: true }
            );

            currentPanel.onDidDispose(() => {
                currentPanel = undefined;
            });
        }

        const updateWebviewContent = () => {
            const filePath = vscode.workspace.workspaceFolders ? vscode.workspace.workspaceFolders[0].uri.fsPath : '';
            const htmlPath = path.join(filePath, 'attribute_values.html');
            const htmlContent = fs.readFileSync(htmlPath, 'utf8');
            if (currentPanel) {
                currentPanel.webview.html = htmlContent;
            }
        };

        updateWebviewContent();

        const watcher = vscode.workspace.createFileSystemWatcher(new vscode.RelativePattern(vscode.workspace.workspaceFolders![0], 'attribute_values.html'));
        watcher.onDidChange(updateWebviewContent);
        watcher.onDidDelete(updateWebviewContent);
        watcher.onDidCreate(updateWebviewContent);

    });

	
	const server = net.createServer(socket => {
		socket.on('data', data => {
			let message = data.toString().trim();
			if (message === '1') {
				vscode.commands.executeCommand('agnosis.HighlightSilver');
			}
		});
	});
	
	server.listen(19387, '127.0.0.1', () => {
		console.log('Server listening on port 19387');
	});
	


    context.subscriptions.push(showAttributeValuesHtml);
	context.subscriptions.push(disposable);
	context.subscriptions.push(launchSilver_dispoable);
	context.subscriptions.push(run_traslator);
	context.subscriptions.push(run_compiler);
	context.subscriptions.push(HighlightSilver);
	context.subscriptions.push({ dispose: () => server.close() });
}

// This method is called when your extension is deactivated
export function deactivate() {
	if (server) {
        server.close();
    }

}
